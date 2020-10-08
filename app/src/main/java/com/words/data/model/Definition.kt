package com.words.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Definition(
    @SerializedName("definition")
    @Expose
    val definition: String,

    @SerializedName("permalink")
    @Expose
    val permalink: String,

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("defid")
    @Expose
    val defid: Long,

    @SerializedName("current_vote")
    @Expose
    val currentVote: String,

    @SerializedName("written_on")
    @Expose
    val writtenOn: String,

    @SerializedName("example")
    @Expose
    val example: String,

    @SerializedName("thumbs_up")
    @Expose
    val thumbsUp: Long,

    @SerializedName("thumbs_down")
    @Expose
    val thumbsDown: Long

) : Parcelable