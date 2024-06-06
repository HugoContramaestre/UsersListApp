package com.userslistapp.domain

import com.userslistapp.domain.error.ErrorDomain

sealed interface DataState <out T>{
    data class  Success<T>(val data: T): DataState<T>
    data class  Error(val error: ErrorDomain): DataState<Nothing>
}

sealed interface NetworkResult<T : Any>
class ApiSuccess<T : Any>(val data: T?) : NetworkResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>