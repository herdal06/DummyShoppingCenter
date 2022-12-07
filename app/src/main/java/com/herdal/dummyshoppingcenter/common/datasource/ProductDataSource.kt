package com.herdal.dummyshoppingcenter.common.datasource

import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    interface Remote {
        suspend fun getProducts(): Flow<PagingData<ProductDto>>
        suspend fun getById(id: Int): ProductDto
    }

    interface Local {

    }
}