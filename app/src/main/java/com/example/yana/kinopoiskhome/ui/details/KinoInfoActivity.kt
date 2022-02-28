package com.example.yana.kinopoiskhome.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yana.kinopoiskhome.R
import com.example.yana.kinopoiskhome.databinding.ActivityKinoInfoBinding
import com.example.yana.kinopoiskhome.ui.TreilerAdapter
import com.example.yana.kinopoiskhome.ui.main.MainActivity
import com.example.yana.kinopoiskhome.ui.search.SearchActivity
import com.example.yana.kinopoiskhome.ui.treiler.TreilerActivity
import com.example.yana.kinopoiskhome.utils.MediaPlayer
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class KinoInfoActivity : AppCompatActivity() {

    private val viewModel: KinoInfoViewModel by viewModel()
    private lateinit var binding: ActivityKinoInfoBinding
    private val filmId by lazy { intent.getIntExtra(FILM_ID, -1) }
    private val treilerFilm by lazy { intent.getIntExtra(TREILER_FILM, -1) }
    private val playerContract: MediaPlayer by inject()
    private val adapterTreiler by lazy {TreilerAdapter{
        TreilerActivity.open(this, it)
    }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKinoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerTreiler.adapter = adapterTreiler
        binding.imgPoster.transitionName = "image${filmId}"
        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        viewModel.loadFilmId(filmId)
        viewModel.loadTrillerFilms(filmId)
        viewModel.filmId.observe(this, {film ->
            Picasso.get().load(film?.posterUrlPreview).placeholder(R.drawable.ic_baseline_image_24).into(binding.imgPoster)
            binding.tvIdFilm.text = film?.description
            binding.rating.text = film?.ratingKinopoisk.toString()
            binding.year.text = film?.year.toString()
            binding.genres.text = film?.genres?.joinToString { it.genre }

            viewModel.filmTreiler.observe(this, {
                adapterTreiler.update(it?.trailer ?: emptyList(), film?.posterUrlPreview ?: "")
            })
        })
    }

    private fun setupListeners() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }


    companion object{
        const val FILM_ID = "filmId"
        const val TREILER_FILM = "treiler_film"
         fun openDetailsActivity(context: Context, filmId: Int, treilerFilm: String) {
            val intent = Intent(context, KinoInfoActivity::class.java)
            intent.putExtra(FILM_ID, filmId)
             intent.putExtra(TREILER_FILM, treilerFilm)
            context.startActivity(intent)
        }
    }
}