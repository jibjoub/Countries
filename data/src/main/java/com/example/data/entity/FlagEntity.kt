package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class FlagEntity(
    @SerializedName("png") val url: String,
    @SerializedName("alt") val description: String
)
