package com.reemHazzaa.jobsapp.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reemHazzaa.jobsapp.utils.JOBS_DATABASE_NAME

@Database(entities = [], version = 1, exportSchema = false)
abstract class JobsRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: JobsRoomDatabase? = null
        fun getDatabase(context: Context): JobsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobsRoomDatabase::class.java,
                    JOBS_DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()   // temp solution for migration
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}