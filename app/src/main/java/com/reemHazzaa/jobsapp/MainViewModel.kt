package com.reemHazzaa.jobsapp

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reemHazzaa.jobsapp.data.Repository
import com.reemHazzaa.jobsapp.data.dataSource.remote.NetworkResult
import com.reemHazzaa.jobsapp.data.model.ResponseJobs
import com.reemHazzaa.jobsapp.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val isConnected = NetworkMonitor.isNetworkConnected

    var jobsResponse: MutableLiveData<NetworkResult<ResponseJobs>> = MutableLiveData()

    fun getJobs(query: String) = viewModelScope.launch {
        getJobsSafeCall(query)
    }

    private suspend fun getJobsSafeCall(query: String) {
        jobsResponse.value = NetworkResult.Loading()
        if (isConnected) {
            try {
                val response = repository.remote.getJobs(query)
                jobsResponse.value = handleJobsResponse(response)
            } catch (e: Exception) {
                e.printStackTrace()
                jobsResponse.value = NetworkResult.Error(getString(R.string.no_jobs))
            }
        } else {
            jobsResponse.value = NetworkResult.Error(getString(R.string.no_internet_connection))
        }
    }

    private fun handleJobsResponse(response: Response<ResponseJobs?>?): NetworkResult<ResponseJobs>? {
        when {
            response == null -> {
                return NetworkResult.Error(getString(R.string.no_api_response))
            }
            response.body() == null -> {
                return NetworkResult.Error(getString(R.string.no_jobs))
            }
            response.body()!!.jobsList.isNullOrEmpty() -> {
                return NetworkResult.Error(getString(R.string.no_jobs))
            }
            response.isSuccessful -> {
                val jobs = response.body()
                return NetworkResult.Success(jobs!!)
            }
            else -> {
                return NetworkResult.Error(response.message())

            }
        }
    }

    private fun getString(stringID: Int) = Resources.getSystem().getString(stringID)

}