package com.userslistapp.domain.models

data class UserDomain(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
){
    companion object {
        fun empty(): UserDomain = UserDomain(
            id = 0,
            email = "",
            firstName = "",
            lastName = "",
            avatar = ""
        )
    }
}
