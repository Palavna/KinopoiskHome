package com.example.yana.kinopoiskhome.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.example.yana.kinopoiskhome.databinding.ItemMainBinding
import com.example.yana.kinopoiskhome.ui.Kinopoisk100Adapter
import com.example.yana.kinopoiskhome.ui.KinopoiskAdapter
import com.example.yana.kinopoiskhome.ui.main.MainScreenTypes.*
import com.example.yana.kinopoiskhome.utils.Decorator

class MainAdapter(private val listener: MainAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val list: ArrayList<MainStructureModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TOP100.namber) {
            val binding =
                ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MainVH100(binding, listener)
        } else {
            val binding =
                ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MainVH250(binding, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TOP100.namber) {
            (holder as MainVH100).bind(list[position])
        } else {
            (holder as MainVH250).bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].type == TOP100.name) {
            TOP100.namber
        } else {
            TOP250.namber
        }
    }

    override fun getItemCount() = list.size

    fun update(list: ArrayList<MainStructureModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}


class MainVH100(private val binding: ItemMainBinding, private val listener: MainAdapterListener) : RecyclerView.ViewHolder(binding.root) {
    private val adapter100 by lazy {
                Kinopoisk100Adapter { item, view->
                    listener.clickTop100(item, view)
//            openDetailsActivity(
//                this,
//                item.filmId,
//                treilerFilm = String(),
//                view
//            )
        }
    }
    init {
        binding.recycler.adapter = adapter100
        binding.recycler.addItemDecoration(Decorator())
    }

    fun bind(mainStructureModel: MainStructureModel) {
        mainStructureModel.films100?.let { adapter100.update(it) }
        binding.tvTitle.text = mainStructureModel.title
    }
}

class MainVH250(private val binding: ItemMainBinding,private val listener: MainAdapterListener) : RecyclerView.ViewHolder(binding.root) {
    private val adapter by lazy {
        KinopoiskAdapter { item, view ->
            listener.clickTop250(item, view)
//            openDetailsActivity(
//                this,
//                item.filmId,
//                treilerFilm = String(),
//                view
//            )
        }
    }

    init {
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(Decorator())
    }
    fun bind(mainStructureModel: MainStructureModel) {
        mainStructureModel.films250?.let { adapter.update(it) }
        binding.tvTitle.text = mainStructureModel.title
    }
}
