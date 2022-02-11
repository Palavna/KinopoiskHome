package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.databinding.ItemSearchFilmsBinding
import com.example.yana.kinopoiskhome.utils.diffutils.SearchDiffUtils
import com.example.yana.kinopoiskhome.utils.ratingViewBackground
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class SearchAdapter(private val listener: (Films100, ShapeableImageView) -> Unit) :
    PagingDataAdapter<Films100, SearchVH>(SearchDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchVH.create(parent, listener)

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class SearchVH(private val binding: ItemSearchFilmsBinding, private val listener: (Films100, ShapeableImageView) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(mainFilms: Films100) {
        val icon = mainFilms.posterUrlPreview
        Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_image_24).into(binding.icon250)
        binding.tvFilms250.text = mainFilms.nameRu
        binding.ratingTv250.text = mainFilms.rating
        binding.one1.ratingViewBackground(mainFilms.rating)

        binding.yearTv250.text = mainFilms.year.toString()
        binding.tvGenres.text = mainFilms.genres.joinToString(", ") { it.genre }

        binding.searchPoster.setOnClickListener {
            listener.invoke(mainFilms, binding.icon250)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: (Films100, ShapeableImageView) -> Unit): SearchVH {
            val binding =
                ItemSearchFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchVH(binding, listener)
        }
    }
}