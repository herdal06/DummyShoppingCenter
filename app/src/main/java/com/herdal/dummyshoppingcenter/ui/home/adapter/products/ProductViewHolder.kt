package com.herdal.dummyshoppingcenter.ui.home.adapter.products

import androidx.recyclerview.widget.RecyclerView
import com.herdal.dummyshoppingcenter.databinding.ItemProductBinding
import com.herdal.dummyshoppingcenter.ui.home.ProductItemUiState
import com.herdal.dummyshoppingcenter.utils.ext.executeWithAction

class ProductViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductItemUiState) = binding.apply {
        binding.executeWithAction {
            this.product = product
        }
    }
}