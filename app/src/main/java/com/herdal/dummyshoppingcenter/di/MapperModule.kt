package com.herdal.dummyshoppingcenter.di

import com.herdal.dummyshoppingcenter.common.ProductMapper
import com.herdal.dummyshoppingcenter.common.ProductMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object MapperModule {

    @Provides
    @Singleton
    fun provideProductMapper(
        @IoDispatcher defaultDispatcher: CoroutineDispatcher,
    ): ProductMapper = ProductMapperImpl(defaultDispatcher)
}