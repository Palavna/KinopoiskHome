package com.example.yana.kinopoiskhome.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.example.yana.kinopoiskhome.data.prefs.SharedPreference
import com.example.yana.kinopoiskhome.utils.debounce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SearchViewModel(val iteractor: KinopoiskIteractor): ViewModel() {

    val progress = MutableLiveData(false)
    val filmsLiveData = MutableLiveData<List<Films100>>()

    fun loadPosts(): Flow<PagingData<Films100>> {
        return Pager(
            PagingConfig(pageSize = 20, prefetchDistance = 5),
            pagingSourceFactory = { SearchFilmsSource(iteractor) }).flow.cachedIn(viewModelScope)
    }

    private fun search(query: String){
       SharedPreference.saveSearchQuery(query)
    }
}