package com.example.yana.kinopoiskhome.data.network

import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerFilms
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainFilms
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskService {

    @GET("v2.2/films/top")
    suspend fun loadTop250Films(
        @Query("type") type: String,
        @Query("page") page: Int
    ): MainFilms<Films>?

    @GET("v2.2/films/top")
    suspend fun loadTop100Films(
        @Query("type") type: String,
        @Query("page") page: Int
    ): MainFilms<Films100>?

    @GET("v2.2/films/{id}")
    suspend fun loadFilmId(
        @Path("id") id: Int
    ): FilmsId?

    @GET("v2.2/films/{id}/videos")
    suspend fun loadTreilerFilms(
        @Path("id") id: Int
    ): TreilerFilms?

    @GET("v2.1/films/search-by-keyword")
    suspend fun searchFilms(
        @Query("keyword") query: String,
        @Query("page") page: Int
    ): MainFilms<Films100>?

    @GET("https://kinopoiskhome-c9a5b-default-rtdb.europe-west1.firebasedatabase.app/.json")
    suspend fun getFaireBaseFromDB(): List<MainStructureModel>?
}