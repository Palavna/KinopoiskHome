package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.databinding.Item250FilmsBinding
import com.squareup.picasso.Picasso

class KinopoiskAdapter(private val listener: (Films) -> Unit): RecyclerView.Adapter<KinopoiskVH>() {

    private val list = arrayListOf<Films>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinopoiskVH {
        val binding = Item250FilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KinopoiskVH(binding, listener)
    }

    override fun onBindViewHolder(holder: KinopoiskVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(dailyModel: List<Films>) {
        list.clear()
        list.addAll(dailyModel)
        notifyDataSetChanged()
    }
}

class KinopoiskVH(val binding: Item250FilmsBinding, private val listener: (Films) -> Unit) : RecyclerView.ViewHolder(binding.root){

    fun bind(mainFilms: Films){
        val icon = mainFilms.posterUrlPreview
        Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_image_24).into(binding.icon250)
        binding.tvFilms250.text = mainFilms.nameRu
        binding.ratingTv250.text = mainFilms.rating.toString()
        binding.yearTv250.text = mainFilms.year.toString()
        binding.tvGenres.text = mainFilms.genres.joinToString(", ") { it.genre }

        binding.mainPoster.setOnClickListener {
            listener.invoke(mainFilms)
        }
    }
}