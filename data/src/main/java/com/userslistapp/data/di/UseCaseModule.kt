package com.userslistapp.data.di

import com.userslistapp.domain.repository.UserRepository
import com.userslistapp.domain.usecases.GetUserDetailUseCase
import com.userslistapp.domain.usecases.GetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserListUseCase(userRepository: UserRepository) = GetUserListUseCase(userRepository)

    @Provides
    @ViewModelScoped
    fun provideGetUserDetailUseCase(userRepository: UserRepository) = GetUserDetailUseCase(userRepository)

}