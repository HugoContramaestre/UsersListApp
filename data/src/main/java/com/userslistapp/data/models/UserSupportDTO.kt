package com.userslistapp.data.models

import com.google.gson.annotations.SerializedName

data class UserSupportDTO(
    @SerializedName("url") val url: String?,
    @SerializedName("text") val text: String?,
)
