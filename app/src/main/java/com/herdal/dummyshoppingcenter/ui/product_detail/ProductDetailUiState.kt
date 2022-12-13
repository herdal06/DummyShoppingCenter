package com.herdal.dummyshoppingcenter.ui.product_detail

import com.herdal.dummyshoppingcenter.common.BaseUiState
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel

data class ProductDetailUiState(private val product: ProductUiModel) : BaseUiState() {

    fun getDescription() = product.description

    fun getBrand() = product.brand

    fun getTitle() = product.title

    fun getCategoryName() = product.category

    fun getPrice() = product.price
}