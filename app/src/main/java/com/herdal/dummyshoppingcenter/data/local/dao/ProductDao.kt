package com.herdal.dummyshoppingcenter.data.local.dao

import androidx.room.*
import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductEntity>>

    @Delete
    suspend fun delete(productEntity: ProductEntity)
}