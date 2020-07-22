package com.mkndmail.nasaphotooftheday.utilis

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

fun getVideoPathFromYoutubeURL(youTubeUrl: String): String {
    return youTubeUrl.substringAfter("embed/")
        .substringBefore("?rel")
}