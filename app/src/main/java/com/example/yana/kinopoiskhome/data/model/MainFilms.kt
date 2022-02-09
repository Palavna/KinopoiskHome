package com.example.yana.kinopoiskhome.data.model

import com.google.gson.annotations.SerializedName

data class MainFilms<T>(
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("keyword") val keyword: String?,
    @SerializedName("films") val films: List<T>,
    @SerializedName("icon") val icon : String?
)
