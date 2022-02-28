package com.example.yana.kinopoiskhome.di

import androidx.room.Room
import com.example.yana.kinopoiskhome.data.db.FilmAppDatabase
import com.example.yana.kinopoiskhome.data.db.FilmsDao
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractorImpl
import com.example.yana.kinopoiskhome.data.network.RetrofitBuilder
import com.example.yana.kinopoiskhome.data.repository.FilmRepository
import com.example.yana.kinopoiskhome.data.repository.FilmRepositoryImpl
import com.example.yana.kinopoiskhome.ui.details.KinoInfoViewModel
import com.example.yana.kinopoiskhome.ui.main.MainViewModel
import com.example.yana.kinopoiskhome.ui.search.SearchViewModel
import com.example.yana.kinopoiskhome.utils.MediaPlayer
import com.example.yana.kinopoiskhome.utils.MediaPlayerImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val kinopoiskModules by lazy {
    loadKoinModules(
        listOf(
            networkModule,
            viewModelModule,
            iteractorModules,
            dbModule,
            utilsModule,
            repositoryModel
        )
    )
}
val networkModule = module {
    single {
        RetrofitBuilder.buildRetrofit()
    }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { KinoInfoViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}
val iteractorModules = module {
    single<KinopoiskIteractor> { KinopoiskIteractorImpl(get()) }
}
val dbModule = module {
    single { Room.databaseBuilder(get(), FilmAppDatabase::class.java, "weather")
        .allowMainThreadQueries()
        .build()
    }
    single { get<FilmAppDatabase>().getFilmDao()}
}
val utilsModule = module {
    single<MediaPlayer> { MediaPlayerImpl() }
}

val repositoryModel = module {
    single <FilmRepository> { FilmRepositoryImpl(get(), get()) }
}
