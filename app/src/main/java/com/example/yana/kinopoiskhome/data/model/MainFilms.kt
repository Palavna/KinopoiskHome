package com.example.yana.kinopoiskhome.data.model

import com.google.gson.annotations.SerializedName

data class MainFilms(
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("films") val films: List<Films>,
    @SerializedName("icon") val icon : String
)
