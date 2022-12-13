package com.herdal.dummyshoppingcenter.di

import android.content.Context
import androidx.room.Room
import com.herdal.dummyshoppingcenter.data.local.AppDatabase
import com.herdal.dummyshoppingcenter.data.local.dao.ProductDao
import com.herdal.dummyshoppingcenter.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideProductDao(
        db: AppDatabase
    ): ProductDao = db.productDao()
}