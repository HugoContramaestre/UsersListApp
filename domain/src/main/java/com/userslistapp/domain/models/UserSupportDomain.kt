package com.userslistapp.domain.models

data class UserSupportDomain(
    val url: String,
    val text: String,
){
    companion object{
        fun empty() = UserSupportDomain("", "")
    }
}
