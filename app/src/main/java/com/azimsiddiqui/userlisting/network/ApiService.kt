package com.azimsiddiqui.userlisting.network

import com.azimsiddiqui.userlisting.data.UserDetailResponse
import com.azimsiddiqui.userlisting.data.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("user/")
    suspend fun getUsers(
        @Header("app-id") api_key:String
    ):Response<UserListResponse>

    @GET("user/{id}")
    suspend fun getUserDetail(
        @Header("app-id") api_key: String,
        @Path("id") id:String
    ):Response<UserDetailResponse>
}