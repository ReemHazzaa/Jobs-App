package com.reemHazzaa.jobsapp.screens

import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.reemHazzaa.jobsapp.R
import com.reemHazzaa.jobsapp.data.model.JobItem
import com.reemHazzaa.jobsapp.screens.jobsList.JobsListFragmentDirections

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

        /**
         * This is a function to observe if the database is empty and
         * show/hide views accordingly.
         */
        @JvmStatic
        @BindingAdapter("android:emptyDatabase")
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("android:sendDataToDetailsFragment")
        fun sendDataToDetailsFragment(view: ConstraintLayout, item:JobItem) {
            view.setOnClickListener {
                val action = JobsListFragmentDirections.actionJobsListFragmentToJobDetailsFragment(item)
                view.findNavController().navigate(action)
            }
        }
    }
}