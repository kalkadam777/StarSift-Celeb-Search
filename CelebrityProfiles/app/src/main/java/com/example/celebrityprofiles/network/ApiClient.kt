package com.example.celebrityprofiles.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("X-Api-Key", "Y0GHk65Ltj0eUL4IZUymdw==Y23YMMMudN4EoNWg")
                .build()
            chain.proceed(newRequest)
        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()




    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.api-ninjas.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(PersonService::class.java)
}