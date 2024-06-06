package com.userslistapp.domain.datasource

import com.userslistapp.domain.DataState
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.models.UserListDomain

interface UserRemoteDataSource {

    suspend fun getUsers(): DataState<UserListDomain>
    suspend fun getUser(userId: Int): DataState<UserDetailDomain>

}