package com.reemHazzaa.jobsapp.data.dataSources.remote

import com.reemHazzaa.jobsapp.data.models.ResponseJobs
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val jobsApi: JobsApi
) {
    suspend fun getJobs(query: String): Response<ResponseJobs?>? {
        return jobsApi.getJobs(query)
    }
}