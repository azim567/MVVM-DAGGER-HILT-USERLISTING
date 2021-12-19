package com.azimsiddiqui.userlisting.data.api

import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.data.model.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

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