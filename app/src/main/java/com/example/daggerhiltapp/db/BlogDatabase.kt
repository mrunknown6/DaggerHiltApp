package com.example.daggerhiltapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.daggerhiltapp.models.Blog

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun getBlogDao(): BlogDao

    companion object {
        const val DB_NAME = "blog_db"
    }
}