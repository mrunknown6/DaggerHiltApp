package com.example.daggerhiltapp.di

import com.example.daggerhiltapp.api.BlogApi
import com.example.daggerhiltapp.api.NetworkMapper
import com.example.daggerhiltapp.db.BlogDao
import com.example.daggerhiltapp.db.CacheMapper
import com.example.daggerhiltapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogApi: BlogApi,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) =
        MainRepository(blogDao, blogApi, cacheMapper, networkMapper)
}