package com.example.yana.kinopoiskhome.data.filmTriller

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class TreilerFilms(
    @PrimaryKey
    var id: Int,
    @SerializedName ("items") val trailer: List<Items>
)