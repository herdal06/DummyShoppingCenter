package com.herdal.dummyshoppingcenter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.herdal.dummyshoppingcenter.data.local.dao.ProductDao
import com.herdal.dummyshoppingcenter.data.local.entity.product.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}