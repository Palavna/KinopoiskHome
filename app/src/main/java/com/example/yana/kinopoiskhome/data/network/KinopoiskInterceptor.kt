package com.example.yana.kinopoiskhome.data.network

import okhttp3.Interceptor
import okhttp3.Response

class KinopoiskInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-API-KEY", "d6c5ea94-6731-4d8d-a858-5196f8c01971")
            .build()

        return chain.proceed(request)
    }
}
