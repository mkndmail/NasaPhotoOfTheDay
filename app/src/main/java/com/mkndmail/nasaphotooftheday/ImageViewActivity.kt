package com.mkndmail.nasaphotooftheday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mkndmail.nasaphotooftheday.MainActivity.Companion.RESPONSE_DATA
import com.mkndmail.nasaphotooftheday.databinding.ActivityImageViewBinding
import com.mkndmail.nasaphotooftheday.network.ApodDataResponse

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

class ImageViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apodData = intent.getParcelableExtra<ApodDataResponse>(RESPONSE_DATA)
        binding.apodData = apodData
    }
}