package com.example.yana.kinopoiskhome.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.MainFilms
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import kotlinx.coroutines.launch

class MainViewModel(val iteractor: KinopoiskIteractor): ViewModel() {

    val films250 = MutableLiveData<MainFilms<Films>>()
    val films100 = MutableLiveData<MainFilms<Films100>>()

    init {
        loadTop250Films()
        loadTop100PopularFilms()
    }

    fun loadTop250Films(){
        viewModelScope.launch {
            kotlin.runCatching {
                val result250 = iteractor.loadTop250FilmsForMainScreen()
                films250.postValue(result250)
                Log.d("asasasasasasa", "dfdfdfdfdf")
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
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}