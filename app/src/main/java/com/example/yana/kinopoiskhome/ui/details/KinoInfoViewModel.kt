package com.example.yana.kinopoiskhome.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerFilms
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.example.yana.kinopoiskhome.data.repository.FilmRepository
import kotlinx.coroutines.launch

class KinoInfoViewModel(val repository: FilmRepository): ViewModel() {

    val filmId = MutableLiveData<FilmsId?>()
    val filmTreiler = MutableLiveData <TreilerFilms?>()
    val treiler = MutableLiveData<TreilerItem<Items>>()


    fun loadFilmId(id: Int){
        viewModelScope.launch {
            kotlin.runCatching {
                val filmsId = repository.loadFilmsId(id)
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
                val  treilerFilm = repository.loadTreilerFilms(id)
                filmTreiler.postValue(treilerFilm)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}