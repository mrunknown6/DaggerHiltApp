package com.example.daggerhiltapp.api

import retrofit2.http.GET

interface BlogApi {

    @GET(BASE_URL_ENDPOINT)
    suspend fun getBlogs(): List<BlogNetworkEntity>

    companion object {
        const val BASE_URL = "https://open-api.xyz/placeholder/"
        const val BASE_URL_ENDPOINT = "blogs"
    }
}