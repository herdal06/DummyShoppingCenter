package com.herdal.dummyshoppingcenter.data.remote.service

import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductDto
import com.herdal.dummyshoppingcenter.data.remote.model.product.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getAll(
        @Query(value = "limit") limit: Int,
        @Query(value = "skip") skip: Int,
    ): ProductResponse

    @GET("products/{id}")
    suspend fun getById(
        @Path(value = "id") id: Int
    ): ProductDto
}
