package com.example.baselibs.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageLoader {
    private fun getRequestCreator(url: String) = Picasso.get().load(url)

    @BindingAdapter(value = ["url", "placeholderDrawable", "errorDrawable"], requireAll = false)
    fun loadImage(
        imageView: ImageView,
        url: String,
        placeholderDrawable: Drawable? = null,
        errorDrawable: Drawable? = null
    ) {
        val requestCreator = getRequestCreator(url)

        placeholderDrawable?.let {
            requestCreator.placeholder(it)
        } ?: requestCreator.placeholder(com.google.android.material.R.drawable.design_fab_background) //需要设置默认图片

        errorDrawable?.let {
            requestCreator.error(it)
        } ?: requestCreator.error(com.google.android.material.R.drawable.design_fab_background) //需要设置默认图片

        requestCreator.into(imageView)
    }
}