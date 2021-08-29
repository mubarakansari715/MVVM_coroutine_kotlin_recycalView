package com.example.mvvm_coroutine_kotlin

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_coroutine_kotlin.Adapters.DataAdapter
import com.example.mvvm_coroutine_kotlin.DataClasses.DataClass
import com.example.mvvm_coroutine_kotlin.Network.NetworkConnection
import com.example.mvvm_coroutine_kotlin.Repository.GetRepository
import com.example.mvvm_coroutine_kotlin.ViewModel.DataModelFactory
import com.example.mvvm_coroutine_kotlin.ViewModel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataViewModel: DataViewModel
    private lateinit var mAdapter: DataAdapter
    private lateinit var networkConnection: NetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ///check Internet
        networkConnection = NetworkConnection(application)
        networkConnection.observe(this, Observer {
            when (it) {
                true -> {
                    val snackbar =
                        Snackbar.make(id_layout, "You are in Online!", Snackbar.LENGTH_SHORT)
                    val view = snackbar.view
                    view.setBackgroundColor(ContextCompat.getColor(this, R.color.grean))
                    snackbar.show()
                }
                false -> {
                    val snackbar =
                        Snackbar.make(id_layout, "You are in Offline!!", Snackbar.LENGTH_SHORT)
                    val view = snackbar.view
                    view.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                    snackbar.show()
                }
            }
        })

        //set Data adapter
        mAdapter = DataAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        getViewData()
    }

    private fun getViewData() {
        val repository = GetRepository()
        val datamodelFactory = DataModelFactory(repository)
        dataViewModel = ViewModelProvider(this, datamodelFactory)[DataViewModel::class.java]
        dataViewModel.FetchData()
        dataViewModel.LiveDataList.observe(this, Observer {
            mAdapter.saveData(it as List<DataClass>)
            progress_circular.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        })
    }
}