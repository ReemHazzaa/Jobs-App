package com.reemHazzaa.jobsapp.dataSources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsDao
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsEntity
import com.reemHazzaa.jobsapp.screens.jobsList.data.room.JobsTypeConverter
import com.reemHazzaa.jobsapp.utils.Constants.Companion.JOBS_DATABASE_NAME

@Database(entities = [JobsEntity::class], version = 2, exportSchema = false)
@TypeConverters(JobsTypeConverter::class)
abstract class JobsRoomDatabase : RoomDatabase() {

    abstract fun jobsDao(): JobsDao

}