package com.herdal.dummyshoppingcenter.common

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.herdal.dummyshoppingcenter.R

fun ImageView.downloadImage(url: String?, placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).fitCenter().error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun getPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f // width size
        centerRadius = 30f // radius size
        start()
    }
}

@BindingAdapter("android:loadImage")
fun loadImage(view: ImageView, url: String?) {
    view.downloadImage(url, getPlaceHolder(view.context))
}

@BindingAdapter("setRatingStatus")
fun TextView.setRatingStatus(rating: Double?) {
    rating?.let {
        if (rating >= 4.5) {
            this.text = resources.getString(R.string.high_rated)
            this.setBackgroundResource(R.color.green)
        }
    }
}

@BindingAdapter("setPriceStatus")
fun TextView.setPriceStatus(price: String?) {
    try {
        price?.let {
            if (price.toInt() >= 500) {
                this.text = resources.getString(R.string.free_shipping)
                this.setBackgroundResource(R.color.orange)
            }
        }
    } catch (e: NumberFormatException) {
        e.printStackTrace()
    }
}