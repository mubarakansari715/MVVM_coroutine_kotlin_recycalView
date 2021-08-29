package com.example.mvvm_coroutine_kotlin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_coroutine_kotlin.Repository.GetRepository

class DataModelFactory (private val getRepository: GetRepository)  : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(getRepository) as T
    }
}