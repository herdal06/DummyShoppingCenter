package com.herdal.dummyshoppingcenter.ui.favorite_products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.herdal.dummyshoppingcenter.databinding.ItemProductLinearBinding
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel

class FavoriteProductAdapter(
    private val onFavProductClick: ((view: View, product: ProductUiModel) -> Unit)?
) :
    ListAdapter<ProductUiModel, FavoriteProductViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ProductUiModel>() {
            override fun areItemsTheSame(
                oldItem: ProductUiModel,
                newItem: ProductUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ProductUiModel,
                newItem: ProductUiModel
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductViewHolder =
        FavoriteProductViewHolder(
            ItemProductLinearBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onFavProductClick
        )

    override fun onBindViewHolder(holder: FavoriteProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(currentItem) }
    }
}