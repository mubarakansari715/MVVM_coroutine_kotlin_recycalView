package com.example.mvvm_coroutine_kotlin.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutine_kotlin.DataClasses.DataClass
import com.example.mvvm_coroutine_kotlin.Repository.GetRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class DataViewModel(private val getRepository: GetRepository) : ViewModel() {
    val LiveDataList: MutableLiveData<List<DataClass>> = MutableLiveData()

    fun FetchData() {

        viewModelScope.launch {
            try {
                val result = getRepository.getRepositoryData()
                LiveDataList.value = result
            } catch (e: Exception) {
                Log.d("myTag", "fetchData: Something is Wrong")
            }
        }
    }
}