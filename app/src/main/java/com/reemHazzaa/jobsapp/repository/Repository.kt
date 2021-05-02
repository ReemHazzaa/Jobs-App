package com.reemHazzaa.jobsapp.repository

import com.reemHazzaa.jobsapp.dataSources.local.LocalDataSource
import com.reemHazzaa.jobsapp.dataSources.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    val remote = remoteDataSource
    val local = localDataSource
}