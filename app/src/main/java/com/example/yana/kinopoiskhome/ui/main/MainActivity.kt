package com.example.yana.kinopoiskhome.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.yana.kinopoiskhome.data.model.MainStructureModel
import com.example.yana.kinopoiskhome.databinding.ActivityMainBinding
import com.example.yana.kinopoiskhome.ui.Kinopoisk100Adapter
import com.example.yana.kinopoiskhome.ui.KinopoiskAdapter
import com.example.yana.kinopoiskhome.ui.details.KinoInfoActivity
import com.example.yana.kinopoiskhome.ui.search.SearchActivity
import com.example.yana.kinopoiskhome.utils.Decorator
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
//    private val adapter by lazy {
//        KinopoiskAdapter { item, view ->
//            openDetailsActivity(
//                this,
//                item.filmId,
//                treilerFilm = String(),
//                view
//            )
//        }
//    }
//    private val adapter100 by lazy {
//        Kinopoisk100Adapter { item, view->
//            openDetailsActivity(
//                this,
//                item.filmId,
//                treilerFilm = String(),
//                view
//            )
//        }
//    }
    private val adapter by lazy { MainAdapter() }

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView()
        setupViewModel()
        setupListeners()


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                viewModel.fromDB(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("dfgdfghdfg", "fghfghj")
            }
        })



    }

    private fun recyclerView() {
//        binding.top250.addItemDecoration(Decorator())
//        binding.top250.adapter = adapter
//        binding.top100.addItemDecoration(Decorator())
        binding.recycler.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.data.observe(this, {
            if (it != null) {
                adapter.update(it)
            }
        }
        )
//        viewModel.films250.observe(this, {
//            adapter.update(it.films)
//        })
//        viewModel.films100.observe(this, {
//            adapter100.update(it.films)
//        })
    }

    private fun setupListeners() {
        binding.imgSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }
//    private fun showNotification(){
//        createNotificationChannel()
//        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
//            .setSmallIcon(R.drawable.film_reel)
//            .setContentTitle("Здравствуйте")
//            .setContentText("Самое время посмотреть хороший фильм!")
//            .setAutoCancel(true)
//            .build()
//        with(NotificationManagerCompat.from(this)){
//            notify(1, builder)
//        }
//    }
//     private fun createNotificationChannel(){
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//             val name = getString(R.string.channel_name)
//             val descriptionText = getString(R.string.channel_description)
//             val importance = NotificationManager.IMPORTANCE_DEFAULT
//             val channel = NotificationChannel(FaireBaseNotificationService.CHANNEL_ID, name, importance).apply {
//                 description = descriptionText
//             }
//             val notificationManager: NotificationManager =
//                 getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//             notificationManager.createNotificationChannel(channel)
//         }
//     }
}