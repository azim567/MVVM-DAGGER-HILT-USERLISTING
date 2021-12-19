package com.azimsiddiqui.userlisting.data.repository.datasource

import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.data.model.UserListResponse
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getUsers(): Response<UserListResponse>
    suspend fun getUserDetail(userId:String):Response<UserDetailResponse>
}