package com.example.yana.kinopoiskhome.utils

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.yana.kinopoiskhome.R
import java.lang.Exception

fun ConstraintLayout.ratingViewBackground(filmRating: String?) {

    val rating = try {
        filmRating?.toDouble()
    } catch (e: Exception){
        this.isVisible = true
        0.0
    }
    if (rating != null)
    when {
        rating < 6.0 -> this.setBackgroundResource(R.drawable.bg_red_rating)
        rating in 6.0..8.0 -> this.setBackgroundResource(R.drawable.bg_yellow_rating)
        else -> this.setBackgroundResource(R.drawable.bg_rating)
    }
    else this.isVisible = true
}