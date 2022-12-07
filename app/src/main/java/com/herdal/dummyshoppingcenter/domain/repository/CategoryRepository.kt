package com.herdal.dummyshoppingcenter.domain.repository

interface CategoryRepository {
    suspend fun getCategories(): List<String>
}