package com.herdal.dummyshoppingcenter.ui.home

import com.herdal.dummyshoppingcenter.common.BaseUiState
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import com.herdal.dummyshoppingcenter.utils.ext.getFormattedPrice

data class ProductItemUiState(private val product: ProductUiModel) : BaseUiState() {

    fun getThumbnail() = product.thumbnail

    fun getTitle() = product.title

    fun getPrice() = product.getFormattedPrice().drop(1)

    fun getRating() = product.rating
}