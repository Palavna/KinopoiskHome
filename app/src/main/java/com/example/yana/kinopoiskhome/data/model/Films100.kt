package com.example.yana.kinopoiskhome.data.model

import com.google.gson.annotations.SerializedName

data class Films100(
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("year") val year: String,
    @SerializedName("filmLength") val filmLength: String,
    @SerializedName("countries") val countries: List<Countries>,
    @SerializedName("genres") val genres: List<Genres>,
    @SerializedName("rating") val rating: String,
    @SerializedName("ratingVoteCount") val ratingVoteCount: Int,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
    @SerializedName("ratingChange") val ratingChange: String,
)
