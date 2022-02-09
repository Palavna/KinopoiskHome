package com.example.yana.kinopoiskhome.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.data.prefs.SharedPreference
import com.example.yana.kinopoiskhome.databinding.ActivitySearchBinding
import com.example.yana.kinopoiskhome.ui.Kinopoisk100Adapter
import com.example.yana.kinopoiskhome.ui.SearchAdapter
import com.example.yana.kinopoiskhome.ui.details.KinoInfoActivity
import com.example.yana.kinopoiskhome.ui.main.MainActivity
import com.example.yana.kinopoiskhome.ui.main.MainViewModel
import com.example.yana.kinopoiskhome.utils.debounce
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
        binding.emptyView.isVisible = binding.search.text.toString().isEmpty()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.loadPosts().collectLatest {
                adapterSearch.submitData(it)
            }
        }
        viewModel.progress.observe(this, {
            binding.progress.isVisible = it
        })
    }
    private fun setupListeners(){
        binding.search.doAfterTextChanged {
            handleClickEventsDebounced(it.toString())
            binding.emptyView.isVisible = it.toString().isEmpty()
            binding.rvFilms.isVisible = it.toString().isNotEmpty()
        }
    }
    val handleClickEventsDebounced = debounce<String>(500, lifecycleScope){
        SharedPreference.saveSearchQuery(it)
        adapterSearch.refresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        SharedPreference.saveSearchQuery("")
    }
}