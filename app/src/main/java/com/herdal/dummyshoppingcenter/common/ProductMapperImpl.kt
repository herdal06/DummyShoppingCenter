package com.herdal.dummyshoppingcenter.common

import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductResponse
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductMapperImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher
) : ProductMapper {
    override suspend fun mapRemoteProductsListToDomain(remoteProducts: ProductResponse): List<ProductUiModel> =
        withContext(defaultDispatcher) {
            remoteProducts.products.map {
                mapRemoteProductToDomain(it)
            }
        }

    override suspend fun mapRemoteProductToDomain(remoteProduct: ProductDto): ProductUiModel =
        ProductUiModel(
            brand = remoteProduct.brand,
            category = remoteProduct.category,
            description = remoteProduct.description,
            discountPercentage = remoteProduct.discountPercentage,
            id = remoteProduct.id,
            images = remoteProduct.images,
            price = remoteProduct.price,
            rating = remoteProduct.rating,
            stock = remoteProduct.stock,
            thumbnail = remoteProduct.thumbnail,
            title = remoteProduct.title
        )
}