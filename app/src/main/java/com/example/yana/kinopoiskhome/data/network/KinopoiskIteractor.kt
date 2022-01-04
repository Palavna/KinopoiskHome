package com.example.yana.kinopoiskhome.data.network

import com.example.yana.kinopoiskhome.data.model.MainFilms

interface KinopoiskIteractor {
    suspend fun loadTop250FilmsForMainScreen(): MainFilms?
    suspend fun loadTop100PopularFilms(): MainFilms?
}


class KinopoiskIteractorImpl(private val network: KinopoiskService): KinopoiskIteractor{

    override suspend fun loadTop250FilmsForMainScreen(): MainFilms? {
        return network.loadTop250Films("TOP_250_BEST_FILMS", 1)
    }

    override suspend fun loadTop100PopularFilms(): MainFilms? {
        return network.loadTop100Films("TOP_100_POPULAR_FILMS", 1)
    }
}