package com.penpab.postsanitizer.api

import com.penpab.postsanitizer.dto.PostItem
import com.penpab.postsanitizer.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WordpressApi {
    @GET("/wp-json/wp/v2/posts?page=2&per_page=5")
    suspend fun fetchPosts(
        @Query("page") page: Int,
        @Query("per_page") per_count: Int
    ): Response<ResponseBody>

    companion object {
        private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WordpressApi::class.java)
        }
    }
}