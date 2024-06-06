package com.userslistapp.domain.error

sealed class ErrorDomain {

    object NotFoundError : ErrorDomain()

    object NetworkError : ErrorDomain()

    object UnknownError : ErrorDomain()
}
