package com.mkndmail.nasaphotooftheday.bindingutils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mkndmail.nasaphotooftheday.R
import com.mkndmail.nasaphotooftheday.network.ApodDataResponse
import com.mkndmail.nasaphotooftheday.network.Status
import com.mkndmail.nasaphotooftheday.utilis.ZoomableImageView
import com.mkndmail.nasaphotooftheday.utilis.getVideoPathFromYoutubeURL

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

@BindingAdapter("loadImage")
fun ImageView.loadImage(apodDataResponse: ApodDataResponse?) {
    apodDataResponse?.let {
//        IN case of video the hdurl is null so we would use url
        /*https://www.youtube.com/embed/sNUNB6CMnE8?rel=0*/
        if (apodDataResponse.mediaType == "video") {
            val videoPath: String = getVideoPathFromYoutubeURL(apodDataResponse.url!!)
            val thumbnailUrl = "https://img.youtube.com/vi/$videoPath/hqdefault.jpg"
            Glide.with(this)
                .asBitmap()
                .load(thumbnailUrl)
                .placeholder(R.drawable.placeholder)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        this@loadImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        } else {
            Glide.with(this).load(apodDataResponse.hdurl)
                .placeholder(R.drawable.placeholder).into(this)
        }
    }

}

@BindingAdapter("showProgressStatus")
fun ProgressBar.showProgressBar(status: Status) {
    if (status == Status.LOADING) this.visibility = VISIBLE
    else this.visibility = GONE
}

@BindingAdapter("setBitmap")
fun ZoomableImageView.setBitmap(apodDataResponse: ApodDataResponse) {
    Glide.with(this)
        .asBitmap()
        .load(apodDataResponse.hdurl)
        .placeholder(R.drawable.placeholder)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                this@setBitmap.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // this is called when imageView is cleared on lifecycle call or for
                // some other reason.
                // if you are referencing the bitmap somewhere else too other than this imageView
                // clear it here as you can no longer have the bitmap
            }
        })
}