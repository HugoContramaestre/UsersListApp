package com.userslistapp.domain.models

data class UserDetailDomain(
    val user: UserDomain,
    val support: UserSupportDomain,
) {
    companion object{
        fun empty() = UserDetailDomain(UserDomain.empty(), UserSupportDomain.empty())
    }
}
