package com.herdal.dummyshoppingcenter.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.herdal.dummyshoppingcenter.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).into(this)
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
fun TextView.setPriceStatus(price: Int?) {
    price?.let {
        if (price >= 500) {
            this.text = resources.getString(R.string.free_shipping)
            this.setBackgroundResource(R.color.orange)
        }
    }
}