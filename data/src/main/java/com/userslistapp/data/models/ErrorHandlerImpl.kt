package com.userslistapp.data.models

import com.userslistapp.domain.error.ErrorDomain
import com.userslistapp.domain.error.ErrorHandler
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(): ErrorHandler {
    override fun getError(throwable: Throwable): ErrorDomain {
        return when (throwable) {
            //is IOException -> ErrorDomain.NetworkError
            is HttpException -> {
                when(throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorDomain.NotFoundError
                    else -> ErrorDomain.UnknownError
                }
            }
            else -> ErrorDomain.UnknownError
        }
    }

    override fun getError(code: Int): ErrorDomain {
        return when(code) {
            404 -> ErrorDomain.NotFoundError
            else -> ErrorDomain.UnknownError
        }
    }
}