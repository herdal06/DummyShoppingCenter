package com.herdal.dummyshoppingcenter.ui.home.adapter.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.herdal.dummyshoppingcenter.databinding.ItemProductBinding
import com.herdal.dummyshoppingcenter.ui.home.ProductItemUiState

class ProductAdapter(
    private val onProductClick: ((productId: Int) -> Unit)?
) : PagingDataAdapter<ProductItemUiState, ProductViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ProductItemUiState>() {
            override fun areItemsTheSame(
                oldItem: ProductItemUiState,
                newItem: ProductItemUiState
            ): Boolean = oldItem.getThumbnail() == newItem.getThumbnail()

            override fun areContentsTheSame(
                oldItem: ProductItemUiState,
                newItem: ProductItemUiState
            ): Boolean = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(currentItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onProductClick
        )
}