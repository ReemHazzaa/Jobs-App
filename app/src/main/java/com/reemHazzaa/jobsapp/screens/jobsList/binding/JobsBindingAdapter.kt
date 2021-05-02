package com.reemHazzaa.jobsapp.screens.jobsList.binding

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.reemHazzaa.jobsapp.R
import com.reemHazzaa.jobsapp.dataSources.remote.NetworkResult
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsEntity
import com.reemHazzaa.jobsapp.utils.makeGone
import com.reemHazzaa.jobsapp.utils.makeVisible

class JobsBindingAdapter {
    companion object {
        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<List<JobItem>>?,
            database: List<JobsEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.makeVisible()
            } else if (apiResponse is NetworkResult.Loading) {
                imageView.makeGone()
            } else if (apiResponse is NetworkResult.Success) {
                imageView.makeGone()
            }
        }

        @BindingAdapter("readApiResponse2", "readDatabase2", requireAll = true)
        @JvmStatic
        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<List<JobItem>>?,
            database: List<JobsEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.makeVisible()
                textView.text = apiResponse.message.toString()
            } else if (apiResponse is NetworkResult.Loading) {
                textView.makeGone()
            } else if (apiResponse is NetworkResult.Success) {
                textView.makeGone()
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
        fun emptyJobsDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

    }
}