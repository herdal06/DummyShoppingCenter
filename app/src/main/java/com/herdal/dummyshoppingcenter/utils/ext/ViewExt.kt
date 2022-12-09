package com.herdal.dummyshoppingcenter.utils.ext

import android.view.View
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.showBalloon(
    context: Context,
    lifecycle: LifecycleOwner,
    balloonText: String,
    textColor: Int,
    bgColor: Int
) =
    this.setOnClickListener {
        val balloon = Balloon.Builder(context)
            .setWidthRatio(0.5f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(balloonText)
            .setTextColorResource(textColor)
            .setTextSize(20f)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(12)
            .setCornerRadius(8f)
            .setBackgroundColorResource(bgColor)
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .setLifecycleOwner(lifecycle)
            .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
            .build()
        this.showAlignRight(balloon)
    }