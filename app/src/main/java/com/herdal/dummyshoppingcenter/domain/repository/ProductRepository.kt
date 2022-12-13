package com.herdal.dummyshoppingcenter.domain.repository

import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<PagingData<ProductUiModel>>
    suspend fun getById(id: Int): ProductUiModel
    suspend fun insertToDb(product: ProductUiModel)
    fun getAllFromDb(): Flow<List<ProductUiModel>>
    suspend fun delete(product: ProductUiModel)
}