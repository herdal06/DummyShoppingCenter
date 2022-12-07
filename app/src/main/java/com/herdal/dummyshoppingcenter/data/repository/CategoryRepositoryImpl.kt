package com.herdal.dummyshoppingcenter.data.repository

import com.herdal.dummyshoppingcenter.common.datasource.CategoryDataSource
import com.herdal.dummyshoppingcenter.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remote: CategoryDataSource.Remote
) : CategoryRepository {
    override suspend fun getCategories(): List<String> = remote.getCategories()
}