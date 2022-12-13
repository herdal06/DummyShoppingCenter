package com.herdal.dummyshoppingcenter.common

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.herdal.dummyshoppingcenter.R
import com.herdal.dummyshoppingcenter.ui.home.ProductItemUiState

fun ImageView.downloadImage(url: String?, placeholder: CircularProgressDrawable) {
    val options =
        RequestOptions().placeholder(placeholder).fitCenter().error(R.mipmap.ic_launcher_round)
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

@BindingAdapter("setProductStatus")
fun TextView.setProductStatus(uiState: ProductItemUiState?) {
    uiState?.let {
        if(uiState.getRating() >= 4.5 && uiState.getPrice() >= 500) {
            this.text = resources.getString(R.string.best_product)
            this.setBackgroundResource(R.color.purple_700)
        }
        else if (uiState.getRating() < 4.5 && uiState.getPrice() >= 500) {
            this.text = resources.getString(R.string.free_shipping)
            this.setBackgroundResource(R.color.orange)
        }
        else if (uiState.getPrice() < 500 && uiState.getRating() >= 4.5) {
            this.text = resources.getString(R.string.high_rated)
            this.setBackgroundResource(R.color.green)
        }
    }
}