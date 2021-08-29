package com.example.mvvm_coroutine_kotlin.Network

import com.example.mvvm_coroutine_kotlin.DataClasses.DataClass
import retrofit2.http.GET

interface ApiInterface {
    @GET("photos")
    suspend fun getData(): List<DataClass>
}