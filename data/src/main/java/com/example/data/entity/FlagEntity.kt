package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class FlagEntity(
    @SerializedName("svg") val url: String?,
    @SerializedName("alt") val description: String?,
)
