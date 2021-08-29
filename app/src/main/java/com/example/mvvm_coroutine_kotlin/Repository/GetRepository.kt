package com.example.mvvm_coroutine_kotlin.Repository

import com.example.mvvm_coroutine_kotlin.DataClasses.DataClass
import com.example.mvvm_coroutine_kotlin.Objects.RetrofitBulder

class GetRepository  {
    suspend fun getRepositoryData ():List<DataClass> = RetrofitBulder.api.getData()
}