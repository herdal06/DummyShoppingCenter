package com.herdal.dummyshoppingcenter.di

import com.herdal.dummyshoppingcenter.data.repository.ProductRepositoryImpl
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}