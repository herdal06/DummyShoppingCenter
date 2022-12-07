package com.herdal.dummyshoppingcenter.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.common.ProductDataSource
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import com.herdal.dummyshoppingcenter.data.remote.pagingsource.ProductPagingSource
import com.herdal.dummyshoppingcenter.data.remote.service.ProductService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productService: ProductService
) : ProductDataSource.Remote {
    override suspend fun getProducts(): Flow<PagingData<ProductDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { ProductPagingSource(productService) }
        ).flow

    override suspend fun getById(id: Int): ProductDto = productService.getById(id)

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}