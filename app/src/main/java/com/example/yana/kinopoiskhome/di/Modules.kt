package com.example.yana.kinopoiskhome.di

import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractor
import com.example.yana.kinopoiskhome.data.network.KinopoiskIteractorImpl
import com.example.yana.kinopoiskhome.data.network.RetrofitBuilder
import com.example.yana.kinopoiskhome.ui.details.KinoInfoViewModel
import com.example.yana.kinopoiskhome.ui.main.MainViewModel
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
            utilsModule
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
}
val iteractorModules = module {
    single<KinopoiskIteractor> { KinopoiskIteractorImpl(get()) }
}
val dbModule = module {
//    single { Room.databaseBuilder(get(), WeatherDataBase::class.java, "weather")
//        .allowMainThreadQueries()
//        .build()
//    }
//    single { get<WeatherDataBase>().getWeatherDao()}
}
val utilsModule = module {
    single<MediaPlayer> { MediaPlayerImpl() }
}

//val repositoryModel = module {
//    single { WeatherRepository(get(), get()) }
//}
