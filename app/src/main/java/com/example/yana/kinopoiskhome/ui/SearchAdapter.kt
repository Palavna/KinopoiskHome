package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.databinding.ItemSearchFilmsBinding
import com.example.yana.kinopoiskhome.utils.ratingViewBackground
import com.squareup.picasso.Picasso
import java.lang.Exception

class SearchAdapter(private val listener: (Films100) -> Unit): RecyclerView.Adapter<SearchVH>() {

    private val list = arrayListOf<Films100>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        val binding = ItemSearchFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchVH(binding, listener)
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(dailyModel: List<Films100>?) {
        list.clear()
        list.addAll(dailyModel ?: emptyList())
        notifyDataSetChanged()
    }
}

class SearchVH(val binding: ItemSearchFilmsBinding, private val listener: (Films100) -> Unit) : RecyclerView.ViewHolder(binding.root){
    fun bind(mainFilms: Films100){
        val icon = mainFilms.posterUrlPreview
        Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_image_24).into(binding.icon250)
        binding.tvFilms250.text = mainFilms.nameRu
        binding.ratingTv250.text = mainFilms.rating
        binding.one1.ratingViewBackground(mainFilms.rating)

        binding.yearTv250.text = mainFilms.year.toString()
        binding.tvGenres.text = mainFilms.genres.joinToString(", ") { it.genre }

        binding.searchPoster.setOnClickListener {
            listener.invoke(mainFilms)
        }
    }
}