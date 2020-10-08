package com.terms.data.repository

import com.terms.data.service.DefinitionResponse
import com.terms.data.service.WebServices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class DefinitionRepositoryImpl(private val webServices: WebServices) : DefinitionRepository {
    override fun getDefinition(term: String): Single<DefinitionResponse> {
        return webServices.findDefinition(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }
}