package com.example.yana.kinopoiskhome.data.network

import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainFilms

interface KinopoiskIteractor {
    suspend fun loadTop250FilmsForMainScreen(): MainFilms<Films>?
    suspend fun loadTop100PopularFilms(): MainFilms<Films100>?
    suspend fun loadFilmId(id: Int): FilmsId?
    suspend fun loadTreilerFilms(id: Int): TreilerItem<Items>?
}


class KinopoiskIteractorImpl(private val network: KinopoiskService): KinopoiskIteractor{

    override suspend fun loadTop250FilmsForMainScreen(): MainFilms<Films>? {
        return network.loadTop250Films("TOP_250_BEST_FILMS", 1)
    }

    override suspend fun loadTop100PopularFilms(): MainFilms<Films100>? {
        return network.loadTop100Films("TOP_100_POPULAR_FILMS", 1)
    }

    override suspend fun loadFilmId(id: Int): FilmsId? {
        return network.loadFilmId(id)
    }

    override suspend fun loadTreilerFilms(id: Int): TreilerItem<Items>? {
        return network.loadTreilerFilms(id)
    }

}