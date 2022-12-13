package com.herdal.dummyshoppingcenter.common

import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductResponse
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel

interface ProductMapper {

    suspend fun mapRemoteProductsListToDomain(remoteProducts: ProductResponse): List<ProductUiModel>

    suspend fun mapRemoteProductToDomain(remoteProduct: ProductDto): ProductUiModel

    suspend fun mapEntityProductToDomain(entityProduct: ProductEntity): ProductUiModel

    suspend fun mapEntityProductListToDomain(entityProducts: List<ProductEntity>): List<ProductUiModel>

    suspend fun mapDomainToEntityProduct(domain: ProductUiModel): ProductEntity

    suspend fun mapDomainListToEntity(domain: List<ProductUiModel>): List<ProductEntity>

    suspend fun mapRemoteProductListToEntity(remoteProducts: ProductResponse): List<ProductEntity>

    suspend fun mapRemoteProductToEntity(remoteProduct: ProductDto): ProductEntity
}