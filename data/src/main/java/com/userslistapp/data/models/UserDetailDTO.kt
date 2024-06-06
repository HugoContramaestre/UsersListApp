package com.userslistapp.data.models

import com.google.gson.annotations.SerializedName

data class UserDetailDTO(
    @SerializedName("data") val data: UserDTO?,
    @SerializedName("support") val support: UserSupportDTO?,
)
