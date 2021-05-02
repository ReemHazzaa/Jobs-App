package com.reemHazzaa.jobsapp.screens.jobsList.data.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem
import com.reemHazzaa.jobsapp.utils.Constants.Companion.TABLE_JOBS

@Entity(tableName = TABLE_JOBS)
class JobsEntity(
    @NonNull var jobs: List<JobItem?>?
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}