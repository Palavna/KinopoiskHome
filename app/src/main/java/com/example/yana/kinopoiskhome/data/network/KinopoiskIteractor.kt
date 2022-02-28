package com.example.yana.kinopoiskhome.data.network

import android.util.Log
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerFilms
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainFilms
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.lang.reflect.Type

interface KinopoiskIteractor {
    suspend fun loadTop250FilmsForMainScreen(): MainFilms<Films>?
    suspend fun loadTop100PopularFilms(): MainFilms<Films100>?
    suspend fun loadFilmId(id: Int): FilmsId?
    suspend fun loadTreilerFilms(id: Int): TreilerFilms?
    suspend fun searchFilms(query: String, page: Int): MainFilms<Films100>?
    suspend fun faireBase(): List<MainStructureModel>?
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

    override suspend fun loadTreilerFilms(id: Int): TreilerFilms? {
        return network.loadTreilerFilms(id)
    }

    override suspend fun searchFilms(query: String, page: Int): MainFilms<Films100>? {
        return network.searchFilms(query, page)
    }

    override suspend fun faireBase(): List<MainStructureModel>? {
//        val database = FirebaseDatabase.getInstance("https://kinopoiskhome-c9a5b-default-rtdb.europe-west1.firebasedatabase.app")
//        val myRef = database.reference
//        myRef.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                fromDB(snapshot)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("dfgdfghdfg", "fghfghj")
//            }
//        })
        val filmFairebase = network.getFaireBaseFromDB()

        return filmFairebase
    }

}