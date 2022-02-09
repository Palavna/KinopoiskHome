package com.example.yana.kinopoiskhome.utils.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.yana.kinopoiskhome.data.model.Films100

class SearchDiffUtils: DiffUtil.ItemCallback<Films100>() {

    override fun areItemsTheSame(oldItem: Films100, newItem: Films100): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Films100, newItem: Films100): Boolean {
        return oldItem.filmId == newItem.filmId
                && oldItem.rating == newItem.rating
                && oldItem.nameRu == newItem.nameRu
                && oldItem.nameEn == newItem.nameEn
                && oldItem.year == newItem.year
    }
}