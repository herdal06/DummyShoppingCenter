package com.herdal.dummyshoppingcenter.di

import com.herdal.dummyshoppingcenter.common.datasource.CategoryDataSource
import com.herdal.dummyshoppingcenter.common.datasource.ProductDataSource
import com.herdal.dummyshoppingcenter.data.local.datasource.ProductLocalDataSource
import com.herdal.dummyshoppingcenter.data.remote.datasource.CategoryRemoteDataSource
import com.herdal.dummyshoppingcenter.data.remote.datasource.ProductRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindProductRemoteDataSource(productRemoteDataSource: ProductRemoteDataSource): ProductDataSource.Remote

    @Binds
    abstract fun bindProductLocalDataSource(productLocalDataSource: ProductLocalDataSource): ProductDataSource.Local

    @Binds
    abstract fun bindCategoryRemoteDataSource(categoryRemoteDataSource: CategoryRemoteDataSource): CategoryDataSource.Remote
}