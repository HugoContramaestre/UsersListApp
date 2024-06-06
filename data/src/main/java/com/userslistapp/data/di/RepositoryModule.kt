package com.userslistapp.data.di

import com.userslistapp.data.repository.UserRepositoryImpl
import com.userslistapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    @ViewModelScoped
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}