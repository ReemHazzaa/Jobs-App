package com.reemHazzaa.jobsapp.data.dataSource.remote

import com.reemHazzaa.jobsapp.data.model.ResponseJobs
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val jobsApi: JobsApi
) {
    suspend fun getJobs(query: String): Response<ResponseJobs?>? {
        return jobsApi.getJobs(query)
    }
}