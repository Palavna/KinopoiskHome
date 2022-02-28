package com.example.yana.kinopoiskhome.data.repository

import com.example.yana.kinopoiskhome.data.db.FilmsDao
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerFilms
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor

interface FilmRepository {
    suspend fun loadTop250FilmsForMainScreen(): List<Films>??
    suspend fun loadTop100PopularFilms(): List<Films100>?
    suspend fun loadFromFairebase(): List<MainStructureModel>?
    suspend fun loadFilmsId(id:Int): FilmsId?
    suspend fun loadTreilerFilms(id: Int): TreilerFilms??

}



class FilmRepositoryImpl(private val iteractor: KinopoiskIteractor, private val filmsDao: FilmsDao): FilmRepository{
    override suspend fun loadTop250FilmsForMainScreen(): List<Films>? {
        val result = try {
            iteractor.loadTop250FilmsForMainScreen()?.films
        }catch (e:Exception){
            null
        }
        return if (result != null){
            filmsDao.deleteAllTop250()
            filmsDao.insertTop250(result)
            result
        }else {
            filmsDao.getTop250()
        }
    }

    override suspend fun loadTop100PopularFilms(): List<Films100>? {
        val result = try {
            iteractor.loadTop100PopularFilms()?.films
        }catch (e:Exception){
            null
        }
        return if (result != null){
            filmsDao.deleteAllTop100()
            filmsDao.insertTop100(result)
            result
        }else{
            filmsDao.getTop100()
        }

    }

    override suspend fun loadFromFairebase(): List<MainStructureModel> {
        val result = try {
            iteractor.faireBase()
        }catch (e:Exception){
            null
        }
        return if (result != null) {
            filmsDao.deleteAllFaireBase()
            filmsDao.insertFaireBase(result)
            result
        } else {
            filmsDao.getFaireBase()
        }
    }

    override suspend fun loadFilmsId(id: Int): FilmsId? {
        val result = try {
            iteractor.loadFilmId(id)
        }catch (e:Exception){
            null
        }
        return if (result != null) {
            filmsDao.insertFilmsId(result)
            result
        }else{
            filmsDao.getFilmsId(id)
        }
    }

    override suspend fun loadTreilerFilms(id: Int): TreilerFilms? {
        val result = try {
            iteractor.loadTreilerFilms(id)
        }catch (e:Exception){
            null
        }
        return if (result != null) {
            result.id = id
            filmsDao.insertTreilerFilms(result)
            result
        }else{
            filmsDao.getTreilerFilms(id)
        }

    }

}