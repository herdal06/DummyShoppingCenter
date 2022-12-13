package com.herdal.dummyshoppingcenter.common

import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
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

    override suspend fun mapEntityProductToDomain(entityProduct: ProductEntity): ProductUiModel =
        ProductUiModel(
            brand = entityProduct.brand,
            category = entityProduct.category,
            description = entityProduct.description,
            discountPercentage = entityProduct.discountPercentage,
            id = entityProduct.id,
            images = entityProduct.images,
            price = entityProduct.price,
            rating = entityProduct.rating,
            stock = entityProduct.stock,
            thumbnail = entityProduct.thumbnail,
            title = entityProduct.title
        )

    override suspend fun mapEntityProductListToDomain(entityProducts: List<ProductEntity>): List<ProductUiModel> =
        withContext(defaultDispatcher) {
            entityProducts.map {
                mapEntityProductToDomain(it)
            }
        }

    override suspend fun mapDomainToEntityProduct(domain: ProductUiModel): ProductEntity =
        ProductEntity(
            brand = domain.brand,
            category = domain.category,
            description = domain.description,
            discountPercentage = domain.discountPercentage,
            id = domain.id,
            images = domain.images,
            price = domain.price,
            rating = domain.rating,
            stock = domain.stock,
            thumbnail = domain.thumbnail,
            title = domain.title
        )

    override suspend fun mapDomainListToEntity(domain: List<ProductUiModel>): List<ProductEntity> =
        withContext(defaultDispatcher) {
            domain.map {
                mapDomainToEntityProduct(it)
            }
        }

    override suspend fun mapRemoteProductListToEntity(remoteProducts: ProductResponse): List<ProductEntity> =
        withContext(defaultDispatcher) {
            remoteProducts.products.map {
                mapRemoteProductToEntity(it)
            }
        }

    override suspend fun mapRemoteProductToEntity(remoteProduct: ProductDto): ProductEntity =
        ProductEntity(
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