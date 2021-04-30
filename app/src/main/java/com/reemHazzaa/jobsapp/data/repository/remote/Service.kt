package com.reemHazzaa.jobsapp.data.repository.remote

import com.reemHazzaa.jobsapp.data.model.ResponseJobs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("positions.json")
    fun getJobs(
        @Query("description") description: String = "api"
    ): Call<ResponseJobs?>?
}