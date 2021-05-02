package com.reemHazzaa.jobsapp.screens.jobsList.binding

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.reemHazzaa.jobsapp.R
import com.reemHazzaa.jobsapp.screens.jobsList.JobsListFragmentDirections
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem

class JobItemBindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImageFromUrl")
        fun loadImageFromUrl(imageView: ImageView, url: String) {
            if (url != "") {
                imageView.load(url) {
                    crossfade(true)
                    crossfade(600)
                    placeholder(R.drawable.ic_company_place_holder)
                    error(R.drawable.ic_error)
                }
            } else {
                imageView.load(R.drawable.ic_company_place_holder)
            }
        }

        @JvmStatic
        @BindingAdapter("sendDataToDetailsFragment")
        fun sendDataToDetailsFragment(view: ConstraintLayout, item: JobItem) {
            view.setOnClickListener {
                val action =
                    JobsListFragmentDirections.actionJobsListFragmentToJobDetailsFragment(item)
                view.findNavController().navigate(action)
            }
        }
    }
}