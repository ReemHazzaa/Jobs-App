package com.reemHazzaa.jobsapp.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reemHazzaa.jobsapp.databinding.ActivityMainBinding
import com.reemHazzaa.jobsapp.utils.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        NetworkMonitor(applicationContext).startNetworkCallback()
    }

    override fun onStop() {
        super.onStop()
        NetworkMonitor(applicationContext).stopNetworkCallback()
    }
}