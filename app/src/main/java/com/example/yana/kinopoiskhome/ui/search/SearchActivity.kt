package com.example.yana.kinopoiskhome.ui.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
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
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.coroutineContext

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private val adapterSearch by lazy {
        SearchAdapter { item, view->
            openDetailsActivity(
                this,
                item.filmId,
                treilerFilm = String(),
            view
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupListeners()
        setupViewModel()
    }

    fun openDetailsActivity(
        context: Context,
        filmId: Int,
        treilerFilm: String,
        view: ShapeableImageView
    ) {
        val intent = Intent(context, KinoInfoActivity::class.java)
        intent.putExtra(KinoInfoActivity.FILM_ID, filmId)
        intent.putExtra(KinoInfoActivity.TREILER_FILM, treilerFilm)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, view, "image${filmId}")
        startActivity(intent, options.toBundle())
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
        adapterSearch.addLoadStateListener {
            val isEmpty = it.refresh is LoadState.NotLoading && adapterSearch.itemCount == 0
                    && binding.search.text.toString().isNotEmpty()
            binding.notFoundView.isVisible = isEmpty
            val progress = it.refresh is LoadState.Loading
            binding.progress.isVisible = progress
        }
    }

    private fun setupListeners() {
        binding.search.doAfterTextChanged {
            handleClickEventsDebounced(it.toString())
        }
    }

    val handleClickEventsDebounced = debounce<String>(500, lifecycleScope) {
        SharedPreference.saveSearchQuery(it)
        adapterSearch.refresh()
        binding.emptyView.isVisible = it.isEmpty()
        binding.rvFilms.isVisible = it.isNotEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        SharedPreference.saveSearchQuery("")
    }
}