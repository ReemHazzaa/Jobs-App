package com.reemHazzaa.jobsapp.data.dataSources.remote

import com.reemHazzaa.jobsapp.data.models.JobItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {
    @GET("positions.json")
    suspend fun getJobs(
        @Query("description") description: String = "api"
    ): Response<List<JobItem?>?>
}