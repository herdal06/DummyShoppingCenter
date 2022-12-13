package com.herdal.dummyshoppingcenter.ui.favorite_products.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.herdal.dummyshoppingcenter.databinding.ItemProductLinearBinding
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.utils.ext.executeWithAction

class FavoriteProductViewHolder(
    private val binding: ItemProductLinearBinding,
    private val onFavProductClick: ((view: View, product: ProductUiModel) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductUiModel) = binding.apply {
        binding.executeWithAction {
            this.product = product
        }

        ivFavoriteProduct.setOnClickListener {
            onFavProductClick?.invoke(ivFavoriteProduct, product)
        }
    }
}