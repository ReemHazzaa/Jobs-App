package com.reemHazzaa.jobsapp.screens

import android.app.Application
import androidx.lifecycle.*
import com.reemHazzaa.jobsapp.R
import com.reemHazzaa.jobsapp.dataSources.remote.NetworkResult
import com.reemHazzaa.jobsapp.repository.Repository
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsEntity
import com.reemHazzaa.jobsapp.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    /** ROOM */
    val readJobs: LiveData<List<JobsEntity>> = repository.local.readJobs().asLiveData()

    private fun updateJobs(jobsEntity: JobsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.updateJobs(jobsEntity)
        }

    private fun insertJobs(jobsEntity: JobsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertJobs(jobsEntity)
        }

    /** RETROFIT */
    private val isConnected = NetworkMonitor.isNetworkConnected

    var jobsResponse: MutableLiveData<NetworkResult<List<JobItem?>?>> = MutableLiveData()

    fun getJobs(query: String) = viewModelScope.launch {
        getJobsSafeCall(query)
    }

    private suspend fun getJobsSafeCall(query: String) {
        jobsResponse.value = NetworkResult.Loading()
        if (isConnected) {
            try {
                val response = repository.remote.getJobs(query)
                jobsResponse.value = handleJobsResponse(response)

                val jobs = jobsResponse.value!!.data
                if (jobs != null) {
                    cacheJobsOffline(jobs)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                jobsResponse.value = NetworkResult.Error(
                    getApplication<Application>().resources.getString(R.string.no_jobs),
                    null
                )
            }
        } else {
            jobsResponse.value = NetworkResult.Error(
                getApplication<Application>().resources.getString(R.string.no_internet_connection),
                null
            )
        }
    }

    fun updateJobsDB(jobs: List<JobItem?>) {
        val jobEntity = JobsEntity(jobs)
        updateJobs(jobEntity)
    }

    private fun cacheJobsOffline(jobs: List<JobItem?>) {
        val jobEntity = JobsEntity(jobs)
        insertJobs(jobEntity)
    }

    private fun handleJobsResponse(response: Response<List<JobItem?>?>?): NetworkResult<List<JobItem?>?> {
        when {
            response == null -> {
                return NetworkResult.Error(
                    getApplication<Application>().resources.getString(R.string.no_api_response),
                    null
                )
            }
            response.body() == null -> {
                return NetworkResult.Error(
                    getApplication<Application>().resources.getString(R.string.no_jobs),
                    null
                )
            }
            response.body()!!.isNullOrEmpty() -> {
                return NetworkResult.Error(
                    getApplication<Application>().resources.getString(R.string.no_jobs),
                    null
                )
            }
            response.isSuccessful -> {
                val jobs = response.body()
                return NetworkResult.Success(jobs!!)
            }
            else -> {
                return NetworkResult.Error(response.message(), null)

            }
        }
    }

}