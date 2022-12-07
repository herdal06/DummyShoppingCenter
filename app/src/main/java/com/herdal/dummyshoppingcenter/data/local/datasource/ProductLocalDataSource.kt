package com.herdal.dummyshoppingcenter.data.local.datasource

import com.herdal.dummyshoppingcenter.common.datasource.ProductDataSource
import com.herdal.dummyshoppingcenter.data.local.dao.ProductDao
import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
import com.herdal.dummyshoppingcenter.domain.uimodel.ProductUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) : ProductDataSource.Local {
    override suspend fun insert(productEntity: ProductEntity) =
        productDao.insert(productEntity)

    override fun getAll(): Flow<List<ProductUiModel>> = productDao.getAll()
}