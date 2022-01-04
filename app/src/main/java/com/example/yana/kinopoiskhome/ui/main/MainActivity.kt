package com.example.yana.kinopoiskhome.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yana.kinopoiskhome.databinding.ActivityMainBinding
import com.example.yana.kinopoiskhome.ui.KinopoiskAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { KinopoiskAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadTop250Films()
        viewModel.loadTop100PopularFilms()
        binding.top250.adapter = adapter
        viewModel.films250.observe(this,{
            adapter.update(it.films)
        })
    }
}