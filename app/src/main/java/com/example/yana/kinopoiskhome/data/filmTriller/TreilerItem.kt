package com.example.yana.kinopoiskhome.data.filmTriller

import com.google.gson.annotations.SerializedName


data class TreilerItem<T> (

	@SerializedName("total") val total : Int?,
	@SerializedName("items") val items : List<T>?
)