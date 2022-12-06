package com.herdal.dummyshoppingcenter.data.remote.service

import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getAll(
        @Query(value = "limit") limit: Int,
        @Query(value = "skip") skip: Int,
    ): ProductResponse
}
