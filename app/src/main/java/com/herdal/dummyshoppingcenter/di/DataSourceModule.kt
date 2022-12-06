package com.herdal.dummyshoppingcenter.di

import com.herdal.dummyshoppingcenter.common.ProductDataSource
import com.herdal.dummyshoppingcenter.data.remote.datasource.ProductRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindProductRemoteDataSource(productRemoteDataSource: ProductRemoteDataSource): ProductDataSource.Remote
}