package com.userslistapp.data.di

import com.userslistapp.data.datasource.UserRemoteDataSourceImpl
import com.userslistapp.domain.datasource.UserRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun provideUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource
}