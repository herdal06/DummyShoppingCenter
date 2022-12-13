package com.herdal.dummyshoppingcenter.data.remote.datasource

import com.herdal.dummyshoppingcenter.common.datasource.CategoryDataSource
import com.herdal.dummyshoppingcenter.data.remote.model.category.CategoryResponse
import com.herdal.dummyshoppingcenter.data.remote.service.CategoryService
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val categoryService: CategoryService
) : CategoryDataSource.Remote {
    override suspend fun getCategories(): CategoryResponse = categoryService.getAll()
}