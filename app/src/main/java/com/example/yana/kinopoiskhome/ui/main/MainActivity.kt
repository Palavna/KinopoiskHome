package com.example.yana.kinopoiskhome.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yana.kinopoiskhome.databinding.ActivityMainBinding
import com.example.yana.kinopoiskhome.ui.Kinopoisk100Adapter
import com.example.yana.kinopoiskhome.ui.KinopoiskAdapter
import com.example.yana.kinopoiskhome.ui.details.KinoInfoActivity
import com.example.yana.kinopoiskhome.ui.search.SearchActivity
import com.example.yana.kinopoiskhome.utils.Decorator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        KinopoiskAdapter {
            KinoInfoActivity.openDetailsActivity(
                this,
                it.filmId,
                treilerFilm = String()
            )
        }
    }
    private val adapter100 by lazy {
        Kinopoisk100Adapter {
            KinoInfoActivity.openDetailsActivity(
                this,
                it.filmId,
                treilerFilm = String()
            )
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView()
        setupViewModel()
        setupListeners()
    }

    private fun recyclerView() {
        binding.top250.addItemDecoration(Decorator())
        binding.top250.adapter = adapter
        binding.top100.addItemDecoration(Decorator())
        binding.top100.adapter = adapter100
    }

    private fun setupViewModel() {
        viewModel.films250.observe(this, {
            adapter.update(it.films)
        })
        viewModel.films100.observe(this, {
            adapter100.update(it.films)
        })
    }

    private fun setupListeners() {
        binding.imgSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}