package com.azimsiddiqui.userlisting.domain.repository

import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.di.Constants
import com.azimsiddiqui.userlisting.presentation.ApiResult
import com.bumptech.glide.load.engine.Resource

interface UserRepository {
    suspend fun getUsers():ApiResult<List<User>?>
    suspend fun getUserDetail(userId:String):ApiResult<UserDetailResponse?>
}