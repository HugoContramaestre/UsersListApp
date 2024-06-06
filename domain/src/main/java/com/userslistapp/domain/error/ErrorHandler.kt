package com.userslistapp.domain.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorDomain
    fun getError(code: Int): ErrorDomain
}