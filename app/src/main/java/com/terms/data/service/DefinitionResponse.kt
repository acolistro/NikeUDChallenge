package com.terms.data.service

import com.google.gson.annotations.SerializedName
import com.terms.data.model.Definition


data class DefinitionResponse(
    @SerializedName("list")
    val terms: List<Definition>
)
