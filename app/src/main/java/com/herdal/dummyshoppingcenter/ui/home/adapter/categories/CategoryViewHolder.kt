package com.herdal.dummyshoppingcenter.ui.home.adapter.categories

import androidx.recyclerview.widget.RecyclerView
import com.herdal.dummyshoppingcenter.databinding.ItemCategoryBinding
import com.herdal.dummyshoppingcenter.ui.home.CategoryItemUiState
import com.herdal.dummyshoppingcenter.utils.ext.executeWithAction

class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(uiState: CategoryItemUiState) = binding.apply {
        binding.executeWithAction {
            this.uiState = uiState
        }
    }
}