package com.example.yana.kinopoiskhome.data.filmTriller

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items (

	@SerializedName("url") val url : String?,
	@SerializedName("name") val name : String?,
	@SerializedName("site") val site : String?
): Parcelable