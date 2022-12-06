package com.herdal.dummyshoppingcenter.di

import com.herdal.dummyshoppingcenter.common.ProductMapper
import com.herdal.dummyshoppingcenter.domain.repository.ProductRepository
import com.herdal.dummyshoppingcenter.domain.usecase.product.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        productRepository: ProductRepository,
        mapper: ProductMapper
    ): GetProductsUseCase = GetProductsUseCase(productRepository, mapper)
}