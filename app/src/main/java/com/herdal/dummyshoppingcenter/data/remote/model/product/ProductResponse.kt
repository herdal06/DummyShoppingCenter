package com.herdal.dummyshoppingcenter.data.remote.model.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "products")
    val products: List<ProductDto>,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "total")
    val total: Int
)