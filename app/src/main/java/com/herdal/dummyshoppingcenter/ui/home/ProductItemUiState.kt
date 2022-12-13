package com.herdal.dummyshoppingcenter.ui.home

import com.herdal.dummyshoppingcenter.common.BaseUiState
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel

data class ProductItemUiState(private val product: ProductUiModel) : BaseUiState() {

    fun getThumbnail() = product.thumbnail

    fun getTitle() = product.title

    fun getPrice() = product.price

    fun getRating() = product.rating

    fun getId() = product.id
}