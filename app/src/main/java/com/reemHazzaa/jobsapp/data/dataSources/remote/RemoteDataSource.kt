package com.reemHazzaa.jobsapp.data.dataSources.remote

import com.reemHazzaa.jobsapp.data.models.JobItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val jobsApi: JobsApi
) {
    suspend fun getJobs(query: String): Response<List<JobItem?>?> {
        return jobsApi.getJobs(query)
    }
}