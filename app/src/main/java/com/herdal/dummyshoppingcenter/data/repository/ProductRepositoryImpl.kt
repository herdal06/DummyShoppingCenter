package com.herdal.dummyshoppingcenter.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.herdal.dummyshoppingcenter.common.ProductDataSource
import com.herdal.dummyshoppingcenter.common.ProductMapper
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remote: ProductDataSource.Remote,
    private val mapper: ProductMapper
) : ProductRepository {
    override suspend fun getProducts(): Flow<PagingData<ProductUiModel>> {
        val domainProduct = remote.getProducts().map { pagingData ->
            pagingData.map { remoteProduct ->
                mapper.mapRemoteProductToDomain(remoteProduct)
            }
        }
        Timber.d("$domainProduct")
        return domainProduct
    }

    override suspend fun getById(id: Int): ProductUiModel {
        val remoteProduct = remote.getById(id)
        return mapper.mapRemoteProductToDomain(remoteProduct)
    }
}