package com.mkndmail.nasaphotooftheday.network

import com.mkndmail.nasaphotooftheday.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

private const val BASE_URL = BuildConfig.BASE_URL

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface DataService {
    @GET("apod")
    suspend fun getApodData(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ApodDataResponse

    @GET("apod")
    suspend fun getApodDataByDate(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY, @Query("date") date: String
    ): ApodDataResponse
}

object Api {
    val retrofitService: DataService by lazy {
        retrofit.create(DataService::class.java)
    }
}