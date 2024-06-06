package com.userslistapp.data.repository

import com.userslistapp.domain.DataState
import com.userslistapp.domain.datasource.UserRemoteDataSource
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.models.UserListDomain
import com.userslistapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun getUserList(): DataState<UserListDomain> {
        return userRemoteDataSource.getUsers()
    }

    override suspend fun getUser(userId: Int): DataState<UserDetailDomain> {
        return userRemoteDataSource.getUser(userId)
    }
}