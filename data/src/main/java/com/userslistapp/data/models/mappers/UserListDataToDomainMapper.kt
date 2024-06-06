package com.userslistapp.data.models.mappers

import com.userslistapp.data.models.UserDTO
import com.userslistapp.data.models.UserListDTO
import com.userslistapp.domain.models.UserDomain
import com.userslistapp.domain.models.UserListDomain

fun UserListDTO.toDomain(): UserListDomain {
    return UserListDomain(
        page = this.page ?: 0,
        perPage = this.perPage ?: 0,
        total = this.total ?: 0,
        totalPages = this.totalPages ?: 0,
        data = this.data?.map { it.toDomain() } ?: emptyList()
    )
}

fun UserDTO.toDomain(): UserDomain {
    return UserDomain(
        id = this.id ?: -1,
        email = this.email ?: "",
        firstName = this.firstName ?: "",
        lastName = this.lastName ?: "",
        avatar = this.avatar ?: ""
    )
}