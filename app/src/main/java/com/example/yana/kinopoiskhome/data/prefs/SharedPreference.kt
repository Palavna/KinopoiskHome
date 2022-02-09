package com.example.yana.kinopoiskhome.data.prefs

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {
    private const val NAME = "text"
    private const val SEARCH_QUERY = "search_query"
    private var preference: SharedPreferences? = null

    fun init(context: Context){
        preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }
    fun saveSearchQuery(query: String){
        preference?.edit()?.putString(SEARCH_QUERY, query)?.apply()
    }
    fun getSearchQuery(): String {
       return preference?.getString(SEARCH_QUERY, "") ?: ""
    }
}