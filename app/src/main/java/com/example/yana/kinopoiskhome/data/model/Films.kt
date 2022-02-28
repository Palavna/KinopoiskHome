package com.example.yana.kinopoiskhome.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Films(
    @PrimaryKey
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("nameRu") val nameRu: String?,
    @SerializedName("nameEn") val nameEn: String?,
    @SerializedName("year") val year: Int?,
    @SerializedName("filmLength") val filmLength: String?,
    @SerializedName("countries") val countries: List<Countries>?,
    @SerializedName("genres") val genres: List<Genres>?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("ratingVoteCount") val ratingVoteCount: Int?,
    @SerializedName("posterUrl") val posterUrl: String?,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String?,
    @SerializedName("ratingChange") val ratingChange: String?,
)
