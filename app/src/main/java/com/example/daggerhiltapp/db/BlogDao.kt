package com.example.daggerhiltapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blog: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun getBlogs(): List<BlogCacheEntity>
}