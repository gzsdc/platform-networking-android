package com.example.baselibs.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.baselibs.R
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
        } ?: requestCreator.placeholder(R.drawable.image_placeholder)

        errorDrawable?.let {
            requestCreator.error(it)
        } ?: requestCreator.error(R.drawable.image_placeholder)

        requestCreator.into(imageView)
    }
}