package com.userslistapp.data.remote

import com.userslistapp.data.models.UserDetailDTO
import com.userslistapp.data.models.UserListDTO
import com.userslistapp.data.utils.RemoteConstants.USER
import com.userslistapp.data.utils.RemoteConstants.USER_LIST
import com.userslistapp.domain.DataState
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET(USER_LIST)
    suspend fun getUsers(@Query("page") page: Int = 0): Response<UserListDTO>

    @GET(USER)
    suspend fun getUser(@Path("user_id") userId: Int): Response<UserDetailDTO>

}