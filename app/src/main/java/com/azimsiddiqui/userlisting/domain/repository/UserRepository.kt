package com.azimsiddiqui.userlisting.domain.repository

import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.di.Constants

interface UserRepository {
    suspend fun getUsers():List<User>?
    suspend fun getUserDetail(userId:String):UserDetailResponse?
}