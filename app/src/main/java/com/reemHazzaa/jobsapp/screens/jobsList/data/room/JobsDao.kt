package com.reemHazzaa.jobsapp.screens.jobsList.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJobs(newJobs: JobsEntity)

    @Update
    suspend fun updateJobs(jobs: JobsEntity)

    @Query("SELECT * FROM TABLE_JOBS ORDER BY id ASC")
    fun readJobs(): Flow<List<JobsEntity>>
}