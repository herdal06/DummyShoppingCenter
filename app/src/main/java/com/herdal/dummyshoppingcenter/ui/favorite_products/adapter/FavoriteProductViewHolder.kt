package com.herdal.dummyshoppingcenter.ui.favorite_products.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.dummyshoppingcenter.databinding.ItemProductLinearBinding
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.utils.ext.getFormattedPrice
import com.herdal.dummyshoppingcenter.utils.ext.loadImage

class FavoriteProductViewHolder(
    private val binding: ItemProductLinearBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductUiModel) = binding.apply {
        tvFavoriteProductBrand.text = product.brand
        ivFavoriteProduct.loadImage(product.thumbnail)
        tvFavoriteProductPrice.text = product.getFormattedPrice()
        tvFavoriteProductTitle.text = product.title
    }
}