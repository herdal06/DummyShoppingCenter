package com.herdal.dummyshoppingcenter.ui.home.adapter.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.herdal.dummyshoppingcenter.databinding.ItemCategoryBinding
import com.herdal.dummyshoppingcenter.ui.home.CategoryItemUiState

class CategoryAdapter(
) : ListAdapter<CategoryItemUiState, CategoryViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CategoryItemUiState>() {
            override fun areItemsTheSame(
                oldItem: CategoryItemUiState,
                newItem: CategoryItemUiState
            ): Boolean =
                oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: CategoryItemUiState,
                newItem: CategoryItemUiState
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        category?.let { holder.bind(category) }
    }
}