package com.reemHazzaa.jobsapp.data.repository.remote

import com.reemHazzaa.jobsapp.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Retrofit {
    companion object {
        fun baseCreation(): Service {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(Service::class.java)
        }

        fun create(url: String): Service {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.apply {
                addInterceptor(logging)
                readTimeout(1, TimeUnit.MINUTES)
                connectTimeout(1, TimeUnit.MINUTES)
                callTimeout(1, TimeUnit.MINUTES)
            }
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(url)
                .client(httpClient.build())
                .build()

            return retrofit.create(Service::class.java)
        }
    }
}