package com.reemHazzaa.jobsapp.view

import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.reemHazzaa.jobsapp.R

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("android:navigateToJobsList")
        fun navigateToJobsList(bt: Button, navigate: Boolean) {
            bt.setOnClickListener {
                if (navigate) {
                    bt.findNavController()
                        .navigate(R.id.action_welcomeFragment_to_jobsListFragment)
                }
            }
        }
    }
}