package com.herdal.dummyshoppingcenter.domain.repository

import androidx.paging.PagingData
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<PagingData<ProductUiModel>>
    suspend fun getById(id: Int): ProductUiModel
}