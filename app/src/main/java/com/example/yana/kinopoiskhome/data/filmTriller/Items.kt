package com.example.yana.kinopoiskhome.data.filmTriller

import com.google.gson.annotations.SerializedName

data class Items (

	@SerializedName("url") val url : String,
	@SerializedName("name") val name : String,
	@SerializedName("site") val site : String
)