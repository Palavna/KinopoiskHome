package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.filmId.FilmsId
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.data.filmTriller.TreilerItem
import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.databinding.ActivityKinoInfoBinding
import com.example.yana.kinopoiskhome.databinding.ItemTreilerFilmsBinding
import com.squareup.picasso.Picasso

class TreilerAdapter(private val listener: (Items) -> Unit): RecyclerView.Adapter<TreilerVH>() {

    private val list = arrayListOf<Items>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreilerVH {
        val binding = ItemTreilerFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TreilerVH(binding, listener)
    }

    override fun onBindViewHolder(holder: TreilerVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(dailyModel: List<Items>) {
        list.clear()
        list.addAll(dailyModel)
        notifyDataSetChanged()
    }
}

class TreilerVH(val binding: ItemTreilerFilmsBinding, private val listener: (Items) -> Unit) : RecyclerView.ViewHolder(binding.root){

    fun bind(treilerFilms: Items){
        binding.one.text = treilerFilms.name
        val icon = treilerFilms.name
        Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_image_24).into(binding.posterImage)
    }
}