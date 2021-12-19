package com.azimsiddiqui.userlisting.data.repository.datasourceimpl

import com.azimsiddiqui.userlisting.data.api.ApiService
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.data.model.UserListResponse
import com.azimsiddiqui.userlisting.data.repository.datasource.UserRemoteDataSource
import com.azimsiddiqui.userlisting.di.Constants.API_KEY
import retrofit2.Response
import javax.inject.Inject


class UserRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : UserRemoteDataSource {

    override suspend fun getUsers(): Response<UserListResponse> = apiService.getUsers(API_KEY)

    override suspend fun getUserDetail(userId: String): Response<UserDetailResponse> = apiService.getUserDetail(API_KEY,userId)
}