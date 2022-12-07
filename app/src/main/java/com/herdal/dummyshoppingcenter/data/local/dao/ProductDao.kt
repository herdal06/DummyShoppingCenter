package com.herdal.dummyshoppingcenter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductEntity>>
}