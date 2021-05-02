package com.reemHazzaa.jobsapp.dataSources.remote

import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val jobsApi: JobsApi
) {
    suspend fun getJobs(query: String): Response<List<JobItem?>?> {
        return jobsApi.getJobs(query)
    }
}