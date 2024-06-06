package com.userslistapp.data.di

import com.userslistapp.data.models.ErrorHandlerImpl
import com.userslistapp.domain.error.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ErrorModule {
    @Binds
    @Singleton
    fun provideErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}