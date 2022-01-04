package com.example.yana.kinopoiskhome.data.network

import com.example.yana.kinopoiskhome.data.model.MainFilms
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskService {

    @GET("v2.2/films/top")
    suspend fun loadTop250Films(
        @Query("type") type: String,
        @Query("page") page: Int
    ): MainFilms?


    @GET("v2.2/films/top")
    suspend fun loadTop100Films(
        @Query("type") type: String,
        @Query("page") page: Int
    ): MainFilms?
}