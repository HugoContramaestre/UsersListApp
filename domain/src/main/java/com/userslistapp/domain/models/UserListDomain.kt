package com.userslistapp.domain.models

data class UserListDomain(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<UserDomain>,
){
    companion object{
        fun empty(): UserListDomain = UserListDomain(
            page = 0,
            perPage = 0,
            total = 0,
            totalPages = 0,
            data = emptyList()
        )
    }
}
