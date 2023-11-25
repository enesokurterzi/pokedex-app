package com.example.pokedexapp.util

import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokedexapp.R
import com.google.android.material.progressindicator.LinearProgressIndicator

fun ImageView.downloadFromUrl(url: String?) {

    val options = RequestOptions()
        .placeholder(
            R.drawable.silhouette
        )

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

@BindingAdapter("android:downloadURL")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url)
}

@BindingAdapter("app:indicatorColor")
fun setIndicatorColor(progressIndicator: LinearProgressIndicator, @ColorInt color: Int) {
    progressIndicator.setIndicatorColor(color)
}