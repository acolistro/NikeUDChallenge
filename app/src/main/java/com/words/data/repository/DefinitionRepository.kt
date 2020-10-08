package com.words.data.repository

import com.words.data.remote.DefinitionResponse
import io.reactivex.Single

interface DefinitionRepository {
    fun findDefinition(word: String): Single<DefinitionResponse>
}