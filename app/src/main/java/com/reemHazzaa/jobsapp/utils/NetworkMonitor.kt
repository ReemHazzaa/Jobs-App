package com.reemHazzaa.jobsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlin.properties.Delegates


class NetworkMonitor(ctx: Context) {
    private var connectivityManager: ConnectivityManager
    private var networkCallback: ConnectivityManager.NetworkCallback

    init {
        connectivityManager =
            ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                isNetworkConnected = true
            }

            override fun onLost(network: Network) {
                isNetworkConnected = false
            }
        }
    }

    companion object {
        var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
            Log.e("Network connectivity", "$newValue")
        }
    }

    fun startNetworkCallback() {
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        try {
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopNetworkCallback() {
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}