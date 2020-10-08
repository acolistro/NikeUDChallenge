package com.terms.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terms.data.model.Definition
import com.terms.data.repository.DefinitionRepository
import com.terms.view.MainActivity
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import java.util.*

class MainViewModel constructor(private val definitionRepository: DefinitionRepository) :
    ViewModel() {
    private val disposable = CompositeDisposable()
    fun getDefinition(word: String) {
        if (word.isEmpty()) {
            loadingState.value = LoadingState.ERROR("Please enter word")
        } else {
            loadingState.value = LoadingState.LOADING
            disposable.add(
                definitionRepository.getDefinition(word)
                    .subscribe({
                        lastFetchedTime = Date()
                        if (it.terms.isEmpty()) {

                            loadingState.value = LoadingState.ERROR("No Definition found")
                        } else {

                            loadingState.value = LoadingState.SUCCESS(it.terms)
                        }
                    }, {
                        lastFetchedTime = Date()

                        it.printStackTrace()
                        loadingState.value = LoadingState.ERROR(
                            when (it) {
                                is UnknownHostException -> "No Network"
                                else -> it.localizedMessage
                            }
                        )


                    })
            )
        }
    }

    var lastFetchedTime: Date? = null

    val loadingState = MutableLiveData<LoadingState>()

    sealed class LoadingState {
        object LOADING : LoadingState()
        data class SUCCESS(val definitions: List<Definition>) : LoadingState()
        data class ERROR(val message: String) : LoadingState()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun getActivity(): Class<out Activity> {
        return MainActivity::class.java
    }

    fun sortByUpVotes() {
        if (loadingState.value is LoadingState.SUCCESS) {
            loadingState.value =
                LoadingState.SUCCESS((loadingState.value as LoadingState.SUCCESS).definitions.sortedBy { it.thumbsUp })
        }
    }

    fun sortByDownVotes() {

        if (loadingState.value is LoadingState.SUCCESS) {
            loadingState.value =
                LoadingState.SUCCESS((loadingState.value as LoadingState.SUCCESS).definitions.sortedBy { it.thumbsDown })
        }
    }
}