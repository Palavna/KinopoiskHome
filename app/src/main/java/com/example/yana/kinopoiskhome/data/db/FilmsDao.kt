package com.example.yana.kinopoiskhome.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerFilms
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import retrofit2.http.DELETE

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTop250(films: List<Films>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTop100(films: List<Films100>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFaireBase(films: List<MainStructureModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilmsId(filmsId: FilmsId?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTreilerFilms(item: TreilerFilms?)

    @Query("SELECT * FROM films")
    fun getTop250(): List<Films>

    @Query("SELECT * FROM films100")
    fun getTop100(): List<Films100>

    @Query("SELECT * FROM mainstructuremodel")
    fun getFaireBase(): List<MainStructureModel>

    @Query("SELECT * FROM filmsid WHERE kinopoiskId = :id")
    fun getFilmsId(id: Int): FilmsId?

    @Query("SELECT * FROM treilerfilms WHERE id = :id")
    fun getTreilerFilms(id: Int): TreilerFilms?

    @Query("DELETE FROM films")
    fun deleteAllTop250()

    @Query("DELETE FROM films100")
    fun deleteAllTop100()

    @Query("DELETE FROM mainstructuremodel")
    fun deleteAllFaireBase()

    @Query("DELETE FROM filmsid")
    fun deleteAllFilmsId()

    @Query("DELETE FROM treilerfilms")
    fun deleteAllTreilerFilms()
}