package com.example.mvvm_coroutine_kotlin.Network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class NetworkConnection(private val connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {
    constructor(application: Application) : this(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val build =NetworkRequest.Builder()
        return connectivityManager.registerNetworkCallback(build.build(),networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        return connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}