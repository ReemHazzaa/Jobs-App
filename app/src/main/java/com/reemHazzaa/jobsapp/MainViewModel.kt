package com.reemHazzaa.jobsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.reemHazzaa.jobsapp.data.Repository
import com.reemHazzaa.jobsapp.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val isConnected = NetworkMonitor.isNetworkConnected

}