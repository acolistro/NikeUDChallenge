package com.words.data.repository

import com.words.data.remote.DefinitionResponse
import com.words.data.remote.WebServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class DefinitionRepositoryImpl(private val webServices: WebServices) : DefinitionRepository {
    override fun findDefinition(word: String): Single<DefinitionResponse> {
        return webServices.findDefinition(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }
}