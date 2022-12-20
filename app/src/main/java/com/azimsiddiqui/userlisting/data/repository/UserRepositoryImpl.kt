package com.azimsiddiqui.userlisting.data.repository

import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.data.repository.datasource.UserRemoteDataSource
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import com.azimsiddiqui.userlisting.presentation.ApiResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getUsers(): ApiResult<List<User>?> {
        val response = userRemoteDataSource.getUsers()
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResult.Success(data = it.data)
                }
            }
            ApiResult.Error(message = response.message())
        } catch (e: Exception) {
            ApiResult.Error(message = e.message.toString())
        }
    }

    override suspend fun getUserDetail(userId: String): ApiResult<UserDetailResponse?> {
        return try {
            val response = userRemoteDataSource.getUserDetail(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResult.Success(data = it)
                }
            }
            ApiResult.Error(message = response.message())
        } catch (e: Exception) {
            ApiResult.Error(message = e.message.toString())
        }
    }
}