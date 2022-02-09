package com.example.yana.kinopoiskhome.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.example.yana.kinopoiskhome.data.prefs.SharedPreference
import retrofit2.HttpException
import java.io.IOException

class SearchFilmsSource(val iteractor: KinopoiskIteractor): PagingSource<Int, Films100>() {
    override fun getRefreshKey(state: PagingState<Int, Films100>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Films100> {
        val page = params.key ?: 1
        return try {
            val response = iteractor.searchFilms(SharedPreference.getSearchQuery(), page)
            val data = response?.films ?: emptyList()

            val nextKey = if (data.isNullOrEmpty()) {
                null
            }else {
                page + 1
            }

            LoadResult.Page(data, if (page == 1) null else page - 1, nextKey)

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}