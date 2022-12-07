package com.herdal.dummyshoppingcenter.ui.home

import com.herdal.dummyshoppingcenter.common.BaseUiState

class CategoryItemUiState(private val category: String) : BaseUiState() {
    fun getName() = category
}