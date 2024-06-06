package com.userslistapp.data.models.mappers

import com.userslistapp.data.models.UserDetailDTO
import com.userslistapp.data.models.UserSupportDTO
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.models.UserDomain
import com.userslistapp.domain.models.UserSupportDomain

fun UserDetailDTO.toDomain(): UserDetailDomain {
    return UserDetailDomain(
        user = data?.toDomain() ?: UserDomain(
            id = -1,
            email = "",
            firstName = "",
            lastName = "",
            avatar = ""
        ),
        support = support?.toDomain() ?: UserSupportDomain(url = "", text = "")
    )
}

fun UserSupportDTO.toDomain(): UserSupportDomain {
    return UserSupportDomain(
        url = this.url ?: "",
        text = this.text ?: ""
    )
}