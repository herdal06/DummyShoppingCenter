package com.herdal.dummyshoppingcenter.common

import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductResponse
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel

interface ProductMapper {

    suspend fun mapRemoteProductsListToDomain(remoteProducts: ProductResponse): List<ProductUiModel>

    suspend fun mapRemoteProductToDomain(remoteProduct: ProductDto): ProductUiModel
}