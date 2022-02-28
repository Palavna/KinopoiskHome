package com.example.yana.kinopoiskhome.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainFilms
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.example.yana.kinopoiskhome.data.repository.FilmRepository
import com.example.yana.kinopoiskhome.ui.main.MainScreenTypes.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class MainViewModel(private val repository: FilmRepository): ViewModel() {

    val films250 = MutableLiveData<List<Films>>()
    val films100 = MutableLiveData<List<Films100>>()
    private var types: ArrayList<MainStructureModel>? = null
    private var films100Data: List<Films100>? = null
    private var films250Data: List<Films>? = null
    val data = MutableLiveData<ArrayList<MainStructureModel>?>()

    init {
        loadTop250Films()
        loadTop100PopularFilms()
        loadFromFirebase()
    }

    fun loadTop250Films(){
        viewModelScope.launch {
            kotlin.runCatching {
                val result250 = repository.loadTop250FilmsForMainScreen()
                films250.postValue(result250)
                films250Data = result250
                validateAllDataDownloaded()
            }.onFailure {
                Log.d("asasasasasasa", "dfdfdfdfdf")
            }
        }
    }

    fun loadTop100PopularFilms(){
        viewModelScope.launch {
            kotlin.runCatching {
                val result100 = repository.loadTop100PopularFilms()
                films100.postValue(result100)
                films100Data = result100
                validateAllDataDownloaded()
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }

    fun loadFromFirebase(){
         viewModelScope.launch {
             kotlin.runCatching {
                 val result = repository.loadFromFairebase()
                 types = ArrayList(result)
                 validateAllDataDownloaded()
                 Log.d("dfgdfghdfg", "fghfghj")
             }.onFailure {
                 Log.d("vvvvvvvvv", "nnnnnnnnnn")
             }
         }


    }

    fun fromDB(snapshot: DataSnapshot) {
        val listType: Type = object: TypeToken<ArrayList<MainStructureModel?>?>() {}.type
        val yourList: ArrayList<MainStructureModel> = Gson().fromJson(Gson().toJson(snapshot.value), listType)
        Log.d("dfgdfghdfg", "fghfghj")

        types = yourList
        validateAllDataDownloaded()
    }

    private fun validateAllDataDownloaded() {
        if (types != null && films100Data != null && films250Data!= null){
            types?.forEach {
                if (it.type == TOP100.name)
                    it.films100 = films100Data
                else if (it.type == TOP250.name)
                    it.films250 = films250Data
            }
            data.postValue(types)
        }
    }
}

enum class MainScreenTypes(val namber: Int){
    TOP100(100), TOP250(111)
}