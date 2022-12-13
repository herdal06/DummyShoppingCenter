package com.herdal.dummyshoppingcenter.data.remote.service

import com.herdal.dummyshoppingcenter.data.remote.model.category.CategoryResponse
import retrofit2.http.GET

interface CategoryService {

    @GET("products/categories")
    suspend fun getAll(): CategoryResponse
}