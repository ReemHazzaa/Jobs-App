package com.reemHazzaa.jobsapp.di

import android.content.Context
import androidx.room.Room
import com.reemHazzaa.jobsapp.dataSources.local.JobsRoomDatabase
import com.reemHazzaa.jobsapp.utils.Constants.Companion.JOBS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        JobsRoomDatabase::class.java,
        JOBS_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: JobsRoomDatabase) = database.jobsDao()
}