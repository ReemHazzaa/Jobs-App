package com.reemHazzaa.jobsapp.dataSources.local

import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsDao
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val jobsDao: JobsDao
) {
    suspend fun insertJobs(newJobs: JobsEntity) {
        jobsDao.insertJobs(newJobs)
    }

    fun readJobs(): Flow<List<JobsEntity>> {
        return jobsDao.readJobs()
    }
}