package com.reemHazzaa.jobsapp.screens.jobsList.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJobs(newJobs: JobsEntity)

    @Query("SELECT * FROM TABLE_JOBS ORDER BY id ASC")
    fun readJobs(): Flow<List<JobsEntity>>
}