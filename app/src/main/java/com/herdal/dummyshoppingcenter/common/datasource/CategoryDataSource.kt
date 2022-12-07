package com.herdal.dummyshoppingcenter.common.datasource

import com.herdal.dummyshoppingcenter.data.remote.model.category.CategoryResponse

interface CategoryDataSource {
    interface Remote {
        suspend fun getCategories(): CategoryResponse
    }
}