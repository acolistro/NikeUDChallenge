package com.terms.data.repository

import com.terms.data.service.DefinitionResponse
import io.reactivex.Single

interface DefinitionRepository {
    fun getDefinition(term: String): Single<DefinitionResponse>
}