package com.words.data.remote

import com.google.gson.annotations.SerializedName
import com.words.data.model.Definition


data class DefinitionResponse(
    @SerializedName("list")
    val words: List<Definition>
)
