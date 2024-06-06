package com.userslistapp.data.datasource

import com.userslistapp.data.models.mappers.toDomain
import com.userslistapp.data.remote.ApiClient
import com.userslistapp.data.remote.handleApi
import com.userslistapp.data.utils.DataLogger
import com.userslistapp.domain.ApiError
import com.userslistapp.domain.ApiException
import com.userslistapp.domain.ApiSuccess
import com.userslistapp.domain.DataState
import com.userslistapp.domain.datasource.UserRemoteDataSource
import com.userslistapp.domain.error.ErrorHandler
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.models.UserListDomain
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val apiClient: ApiClient
    ) : UserRemoteDataSource {

    override suspend fun getUsers(): DataState<UserListDomain> {
        return when(val networkResponse = handleApi { apiClient.getUsers(0) }) {
            is ApiError -> {
                DataLogger.error("getUsers", networkResponse.message ?: "", "UserRemoteDataSourceImpl")
                DataState.Error(errorHandler.getError(networkResponse.code))
            }
            is ApiException -> {
                DataLogger.exception("getUsers", (networkResponse.e as Exception), "UserRemoteDataSourceImpl")
                DataState.Error(errorHandler.getError(networkResponse.e))
            }
            is ApiSuccess -> {
                DataState.Success(networkResponse.data?.toDomain() ?: UserListDomain.empty())
            }
        }
    }

    override suspend fun getUser(userId: Int): DataState<UserDetailDomain> {
        return when(val networkResponse = handleApi { apiClient.getUser(userId) }) {
            is ApiError -> {
                DataLogger.error("getUserDetail", networkResponse.message ?: "", "UserRemoteDataSourceImpl")
                DataState.Error(errorHandler.getError(networkResponse.code))
            }
            is ApiException -> {
                DataLogger.exception("getUserDetail", (networkResponse.e as Exception), "UserRemoteDataSourceImpl")
                DataState.Error(errorHandler.getError(networkResponse.e))
            }
            is ApiSuccess -> {
                DataState.Success(networkResponse.data?.toDomain() ?: UserDetailDomain.empty())
            }
        }
    }
}