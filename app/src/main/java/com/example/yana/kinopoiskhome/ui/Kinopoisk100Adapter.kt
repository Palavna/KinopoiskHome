package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.model.Films100
import com.example.yana.kinopoiskhome.databinding.Item250FilmsBinding
import com.example.yana.kinopoiskhome.utils.ratingViewBackground
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class Kinopoisk100Adapter(private val listener: (Films100, ShapeableImageView) -> Unit): RecyclerView.Adapter<KinopoiskVH100>() {

    private val list = arrayListOf<Films100>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinopoiskVH100 {
        val binding = Item250FilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KinopoiskVH100(binding, listener)
    }

    override fun onBindViewHolder(holder: KinopoiskVH100, position: Int) {
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

class KinopoiskVH100(val binding: Item250FilmsBinding, private val listener: (Films100, ShapeableImageView) -> Unit) : RecyclerView.ViewHolder(binding.root){
    fun bind(mainFilms: Films100){
        val icon = mainFilms.posterUrlPreview
        Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_image_24).into(binding.icon250)
        binding.tvFilms250.text = mainFilms.nameRu
        binding.ratingTv250.text = mainFilms.rating
        binding.one1.ratingViewBackground(mainFilms.rating)

        binding.yearTv250.text = mainFilms.year.toString()
        binding.tvGenres.text = mainFilms.genres?.joinToString(", ") { it.genre }

        binding.mainPoster.setOnClickListener {
            listener.invoke(mainFilms, binding.icon250)
        }
    }
}