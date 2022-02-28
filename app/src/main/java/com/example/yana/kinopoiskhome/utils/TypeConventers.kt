package com.example.yana.kinopoiskhome.utils

import androidx.room.TypeConverter
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.model.Countries
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.data.model.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConventers {

    @TypeConverter
    fun fromCountries(list: List<Countries?>?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toCountries(string: String?): List<Countries?>? {
        val devicesType = object :
        TypeToken<List<Countries?>?>() {}.type
        return Gson()
            .fromJson<List<Countries?>>(string, devicesType)
    }

    @TypeConverter
    fun fromGenres(list: List<Genres?>?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toGenres(string: String?): List<Genres?>? {
        val devicesType = object :
            TypeToken<List<Genres?>?>() {}.type
        return Gson()
            .fromJson<List<Genres?>>(string, devicesType)
    }

    @TypeConverter
    fun fromTop100(list: List<Films100?>?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toTop100(string: String?): List<Films100?>? {
        val devicesType = object :
            TypeToken<List<Films100?>?>() {}.type
        return Gson()
            .fromJson<List<Films100?>>(string, devicesType)
    }

    @TypeConverter
    fun fromTop250(list: List<Films?>?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toTop250(string: String?): List<Films?>? {
        val devicesType = object :
            TypeToken<List<Films?>?>() {}.type
        return Gson()
            .fromJson<List<Films?>>(string, devicesType)
    }

    @TypeConverter
    fun fromTrailerFilms(list: List<Items>?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toTrailerFilms(string: String?): List<Items?>? {
        val devicesType = object :
            TypeToken<List<Items?>?>() {}.type
        return Gson()
            .fromJson<List<Items?>>(string, devicesType)
    }
}