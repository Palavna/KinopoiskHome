package com.example.yana.kinopoiskhome.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainFilms
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.google.firebase.database.DataSnapshot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class MainViewModel(val iteractor: KinopoiskIteractor): ViewModel() {

    val films250 = MutableLiveData<MainFilms<Films>>()
    val films100 = MutableLiveData<MainFilms<Films100>>()
    private var types: ArrayList<MainStructureModel>? = null
    private var films100Data: MainFilms<Films100>? = null
    private var films250Data: MainFilms<Films>? = null
    val data = MutableLiveData<ArrayList<MainStructureModel>?>()

    init {
        loadTop250Films()
        loadTop100PopularFilms()
    }

    fun loadTop250Films(){
        viewModelScope.launch {
            kotlin.runCatching {
                val result250 = iteractor.loadTop250FilmsForMainScreen()
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
                val result100 = iteractor.loadTop100PopularFilms()
                films100.postValue(result100)
                films100Data = result100
                validateAllDataDownloaded()
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
                if (it.type == "TOP100")
                    it.films100 = films100Data
                else if (it.type == "TOP250")
                    it.films250 = films250Data
            }
            data.postValue(types)
        }
    }
}