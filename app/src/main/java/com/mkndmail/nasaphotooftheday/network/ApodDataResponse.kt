package com.mkndmail.nasaphotooftheday.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

@Parcelize
data class ApodDataResponse(
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    @Json(name = "media_type") val mediaType: String?,
    @Json(name = "service_version") val serviceVersion: String?,
    val title: String?,
    val url: String?,
    val error: String?
) : Parcelable