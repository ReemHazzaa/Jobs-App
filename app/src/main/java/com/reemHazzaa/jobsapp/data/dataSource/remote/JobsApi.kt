package com.reemHazzaa.jobsapp.data.dataSource.remote

import com.reemHazzaa.jobsapp.data.model.ResponseJobs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {
    @GET("positions.json")
    suspend fun getJobs(
        @Query("description") description: String = "api"
    ): Call<ResponseJobs?>?
}