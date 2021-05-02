package com.reemHazzaa.jobsapp.data

import com.reemHazzaa.jobsapp.data.dataSources.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    val remote = remoteDataSource
}