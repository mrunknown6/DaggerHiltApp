package com.example.daggerhiltapp.repository

import com.example.daggerhiltapp.api.BlogApi
import com.example.daggerhiltapp.api.NetworkMapper
import com.example.daggerhiltapp.db.BlogDao
import com.example.daggerhiltapp.db.CacheMapper
import com.example.daggerhiltapp.models.Blog
import com.example.daggerhiltapp.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogApi: BlogApi,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun getBlogs(): Flow<DataState<List<Blog>>> =
        flow {
            emit(DataState.Loading)
            delay(1000)

            try {
                val networkBlogs = blogApi.getBlogs()
                val blogs = networkMapper.mapFromEntityList(networkBlogs)
                for (blog in blogs) {
                    blogDao.insert(cacheMapper.mapToEntity(blog))
                }
                val cachedBlogs = blogDao.getBlogs()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}