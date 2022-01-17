package com.example.yana.kinopoiskhome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.databinding.ItemTreilerFilmsBinding
import com.squareup.picasso.Picasso

class TreilerAdapter(private val listener: (Items) -> Unit): RecyclerView.Adapter<TreilerVH>() {

    private val list = arrayListOf<Items>()
    private var poster = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreilerVH {
        val binding = ItemTreilerFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TreilerVH(binding, listener)
    }

    override fun onBindViewHolder(holder: TreilerVH, position: Int) {
        holder.bind(list[position], poster)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(dailyModel: List<Items>, posterUrlPreview: String) {
        poster = posterUrlPreview
        list.clear()
        list.addAll(dailyModel)
        notifyDataSetChanged()
    }
}

class TreilerVH(val binding: ItemTreilerFilmsBinding, private val listener: (Items) -> Unit) : RecyclerView.ViewHolder(binding.root){

    fun bind(treilerFilms: Items, posterUrlPreview: String){
        binding.one.text = treilerFilms.name
        Picasso.get().load(posterUrlPreview).placeholder(R.drawable.ic_baseline_image_24).into(binding.posterImage)
        binding.itemTreiler.setOnClickListener {
            listener.invoke(treilerFilms)
        }
    }
}