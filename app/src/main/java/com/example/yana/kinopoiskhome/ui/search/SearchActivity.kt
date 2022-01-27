package com.example.yana.kinopoiskhome.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.databinding.ActivitySearchBinding
import com.example.yana.kinopoiskhome.ui.Kinopoisk100Adapter
import com.example.yana.kinopoiskhome.ui.SearchAdapter
import com.example.yana.kinopoiskhome.ui.details.KinoInfoActivity
import com.example.yana.kinopoiskhome.ui.main.MainViewModel
import com.example.yana.kinopoiskhome.utils.debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.coroutineContext

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val adapterSearch by lazy { SearchAdapter{ KinoInfoActivity.openDetailsActivity(this, it.filmId, treilerFilm = String())} }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupListeners()
        setupViewModel()
    }

    private fun setupViews() {
        binding.rvFilms.layoutManager = GridLayoutManager(this, 2)
        binding.rvFilms.adapter = adapterSearch
    }

    private fun setupViewModel() {
        viewModel.filmsLiveData.observe(this,{
            adapterSearch.update(it)
        })
    }

    private fun setupListeners(){
        binding.search.doAfterTextChanged {
            viewModel.handleClickEventsDebounced(it.toString())
        }
    }
}