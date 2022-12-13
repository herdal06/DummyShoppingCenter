package com.herdal.dummyshoppingcenter.ui.product_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.herdal.dummyshoppingcenter.R
import com.herdal.dummyshoppingcenter.utils.ext.loadImage

class ImageSlideAdapter(
    private val context: Context,
    private var imageList: List<String>,
) : PagerAdapter() {
    // OLD SCHOOL
    override fun getCount(): Int = imageList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.item_image_slider,
                null
            )
        val ivImages = view.findViewById<ImageView>(R.id.iv_images)

        imageList[position].let {
            ivImages.loadImage(it)
        }

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}