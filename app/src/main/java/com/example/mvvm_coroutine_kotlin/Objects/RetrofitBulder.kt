package com.example.mvvm_coroutine_kotlin.Objects

import com.example.mvvm_coroutine_kotlin.Network.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBulder{
    private val url = "https://jsonplaceholder.typicode.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
    }
    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}