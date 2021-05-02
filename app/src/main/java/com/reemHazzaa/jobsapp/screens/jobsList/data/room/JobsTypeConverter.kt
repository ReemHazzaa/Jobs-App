package com.reemHazzaa.jobsapp.screens.jobsList.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reemHazzaa.jobsapp.screens.jobsList.data.JobItem

class JobsTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun jobsToString(jobs: List<JobItem?>?): String? {
        return gson.toJson(jobs)
    }

    @TypeConverter
    fun stringToJobs(jobsString: String): List<JobItem?>? {
        val listType = object : TypeToken<List<JobItem?>?>() {}.type
        return gson.fromJson(jobsString, listType)
    }

}