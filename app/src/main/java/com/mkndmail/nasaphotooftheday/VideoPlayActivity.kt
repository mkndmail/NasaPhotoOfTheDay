package com.mkndmail.nasaphotooftheday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.mkndmail.nasaphotooftheday.MainActivity.Companion.RESPONSE_DATA
import com.mkndmail.nasaphotooftheday.databinding.ActivityVideoPlayBinding
import com.mkndmail.nasaphotooftheday.network.ApodDataResponse
import com.mkndmail.nasaphotooftheday.utilis.getVideoPathFromYoutubeURL

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

private const val TAG = "VideoPlayActivity"

class VideoPlayActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityVideoPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.youTubePlayerView.initialize(BuildConfig.YOU_TUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    p2: Boolean
                ) {
                    intent?.getParcelableExtra<ApodDataResponse>(RESPONSE_DATA)?.let {
                        player?.loadVideo(getVideoPathFromYoutubeURL(it.url!!))
                        player?.setPlayerStateChangeListener(object :
                            YouTubePlayer.PlayerStateChangeListener {
                            override fun onAdStarted() {
                            }

                            override fun onLoading() {

                            }

                            override fun onVideoStarted() {
                            }

                            override fun onLoaded(p0: String?) {
                            }

                            override fun onVideoEnded() {
                                finish()
                            }

                            override fun onError(p0: YouTubePlayer.ErrorReason?) {
                            }
                        })
                    }
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.d(TAG, "onInitializationFailure: ${p1.toString()}")
                }
            }
        )


    }

}