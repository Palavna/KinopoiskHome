package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.MainFilms
import com.example.yana.kinopoiskhome.databinding.Item250FilmsBinding
import com.squareup.picasso.Picasso

class KinopoiskAdapter(): RecyclerView.Adapter<KinopoiskVH>() {

    private val list = arrayListOf<Films>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinopoiskVH {
        val binding = Item250FilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KinopoiskVH(binding)
    }

    override fun onBindViewHolder(holder: KinopoiskVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(dailyModel: List<Films>?) {
        list.clear()
        list.addAll(dailyModel ?: emptyList())
        notifyDataSetChanged()
    }
}

class KinopoiskVH(val binding: Item250FilmsBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(mainFilms: Films){
        val icon = mainFilms.posterUrlPreview
        Picasso.get().load(icon).into(binding.icon250)
        binding.tvFilms.text = mainFilms.nameRu
    }
}