package com.herdal.dummyshoppingcenter.data.local.datasource

import com.herdal.dummyshoppingcenter.common.datasource.ProductDataSource
import com.herdal.dummyshoppingcenter.data.local.dao.ProductDao
import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao
) : ProductDataSource.Local {
    override suspend fun insert(productEntity: ProductEntity) =
        productDao.insert(productEntity)

    override fun getAll(): Flow<List<ProductEntity>> = productDao.getAll()
    override suspend fun delete(productEntity: ProductEntity) = productDao.delete(productEntity)
}