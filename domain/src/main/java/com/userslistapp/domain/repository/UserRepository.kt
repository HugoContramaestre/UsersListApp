package com.userslistapp.domain.repository

import com.userslistapp.domain.DataState
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.models.UserListDomain

interface UserRepository {
    suspend fun getUserList(): DataState<UserListDomain>
    suspend fun getUser(userId: Int): DataState<UserDetailDomain>
}