package com.userslistapp.data.remote

import com.google.gson.Gson
import com.userslistapp.data.models.ErrorResponse
import com.userslistapp.domain.ApiError
import com.userslistapp.domain.ApiException
import com.userslistapp.domain.ApiSuccess
import com.userslistapp.domain.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful) {
            ApiSuccess(body)
        } else {
            val error = getErrorResponse(response)
            ApiError(code = error.code?.toInt() ?: 100, message = error.description)
        }
    } catch (e: HttpException) {
        ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiException(e)
    }
}

private fun <T: Any> getErrorResponse(response: Response<T>): ErrorResponse {
    return if(response.errorBody() != null){
        val gson = Gson()
        val errorBody = response.errorBody()!!.string()
        gson.fromJson(errorBody, ErrorResponse::class.java)
    } else {
        ErrorResponse(response.code().toString(), response.message())
    }
}