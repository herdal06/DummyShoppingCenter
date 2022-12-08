package com.herdal.dummyshoppingcenter.common.datasource

import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    interface Remote {
        suspend fun getProducts(): Flow<PagingData<ProductDto>>
        suspend fun getById(id: Int): ProductDto
    }

    interface Local {
        suspend fun insert(productEntity: ProductEntity)
        fun getAll(): Flow<List<ProductEntity>>
        suspend fun delete(productEntity: ProductEntity)
    }
}