package com.mkndmail.nasaphotooftheday.network

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

class ApodDataRepository {

    private val apiService by lazy {
        Api.retrofitService
    }

    suspend fun getApodData(): ApodDataResponse {
        lateinit var apiResponse: ApodDataResponse
        apiResponse = try {
            apiService.getApodData()
        } catch (e: Exception) {
            ApodDataResponse(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                error = "${e.message}. Click to try again"
            )
        }
        return apiResponse
    }

    suspend fun getApodDataByDate(dateRequired: String): ApodDataResponse {
        lateinit var apiResponse: ApodDataResponse
        apiResponse = try {
            apiService.getApodDataByDate(date = dateRequired)
        } catch (e: Exception) {
            ApodDataResponse(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "${e.message}. Click to try again"
            )
        }
        return apiResponse
    }
}