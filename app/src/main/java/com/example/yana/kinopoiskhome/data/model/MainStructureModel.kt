package com.example.yana.kinopoiskhome.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MainStructureModel(
    @PrimaryKey
    val id: String,
    val title: String?,
    val type: String?,
    var films100: List<Films100>?,
    var films250: List<Films>?
)