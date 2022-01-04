package com.example.yana.kinopoiskhome.data.network

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    fun buildRetrofit(): KinopoiskService {
        return Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
            .create(KinopoiskService::class.java)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(KinopoiskInterceptor())
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }
}