package com.reemHazzaa.jobsapp.screens

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import coil.load
import com.reemHazzaa.jobsapp.R
import com.reemHazzaa.jobsapp.screens.jobsList.JobsListFragmentDirections
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImageFromUrl")
        fun loadImageFromUrl(imageView: ImageView, url: String) {
            if (url != "") {
                imageView.load(url) {
                    crossfade(true)
                    crossfade(600)
                    placeholder(R.drawable.ic_company_place_holder)
                }
            } else {
                imageView.load(R.drawable.ic_company_place_holder)
            }
        }

        @JvmStatic
        @BindingAdapter("navigateToJobsList")
        fun navigateToJobsList(bt: Button, navigate: Boolean) {
            bt.setOnClickListener {
                if (navigate) {
                    bt.findNavController()
                        .navigate(R.id.action_welcomeFragment_to_jobsListFragment)
                }
            }
        }

        /**
         * This is a function to observe if the database is empty and
         * show/hide views accordingly.
         */
        @JvmStatic
        @BindingAdapter("emptyDatabase")
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
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