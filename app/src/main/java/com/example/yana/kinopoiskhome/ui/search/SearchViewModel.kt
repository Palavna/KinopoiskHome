package com.example.yana.kinopoiskhome.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.example.yana.kinopoiskhome.utils.debounce
import kotlinx.coroutines.launch

class SearchViewModel(val iteractor: KinopoiskIteractor): ViewModel() {

    val progress = MutableLiveData(true)
    val filmsLiveData = MutableLiveData<List<Films100>>()
    val handleClickEventsDebounced = debounce<String>(500, viewModelScope){
        search(it)
    }

    private fun search(query: String){
        viewModelScope.launch {
            kotlin.runCatching {
                val result = iteractor.searchFilms(query)
                filmsLiveData.postValue(result?.films)
                progress.postValue(false)
                Log.d("error", "sdfsdfsdf")
            }.onFailure {
                Log.d("error", it.localizedMessage)
            }
        }
    }
//    fun progressBar(){
//        viewModelScope.launch {
//            runCatching {
//                progress.postValue(false)
//            }.onFailure {
//                Log.d("aaaaaa", "sssssss")
//                progress.postValue(false)
//            }
//        }
//    }
}