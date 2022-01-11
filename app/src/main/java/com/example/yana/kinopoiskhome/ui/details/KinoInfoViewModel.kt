package com.example.yana.kinopoiskhome.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import kotlinx.coroutines.launch

class KinoInfoViewModel(val iteractor: KinopoiskIteractor): ViewModel() {

    val filmId = MutableLiveData<FilmsId>()
    val filmTreiler = MutableLiveData<TreilerItem<Items>>()
    val treiler = MutableLiveData<TreilerItem<Items>>()


    fun loadFilmId(id: Int){
        viewModelScope.launch {
            kotlin.runCatching {
                val filmsId = iteractor.loadFilmId(id)
                filmId.postValue(filmsId)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
    fun loadTrillerFilms(id: Int){
        viewModelScope.launch {
            kotlin.runCatching {
                val  treilerFilm = iteractor.loadTreilerFilms(id)
                filmTreiler.postValue(treilerFilm)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}