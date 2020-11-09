package com.example.daggerhiltapp.di

import android.content.Context
import androidx.room.Room
import com.example.daggerhiltapp.db.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase) =
        blogDatabase.getBlogDao()
}