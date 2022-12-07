package com.herdal.dummyshoppingcenter.data.local.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "discountPercentage")
    val discountPercentage: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "images")
    val images: List<String>,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "stock")
    val stock: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String
)