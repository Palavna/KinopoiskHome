package com.example.yana.kinopoiskhome.data.model

data class MainStructureModel(
    val title: String,
    val type: String,
    var films100: MainFilms<Films100>?,
    var films250: MainFilms<Films>?
)