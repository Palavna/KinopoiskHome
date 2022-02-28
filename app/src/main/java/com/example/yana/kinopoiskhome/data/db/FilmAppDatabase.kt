package com.example.yana.kinopoiskhome.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerFilms
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.example.yana.kinopoiskhome.utils.TypeConventers

@Database(entities = [Films::class, Films100::class, MainStructureModel::class, FilmsId::class, TreilerFilms::class], version = 1)
@TypeConverters(TypeConventers::class)
abstract class FilmAppDatabase: RoomDatabase() {
    abstract fun getFilmDao(): FilmsDao
}