package com.azimsiddiqui.userlisting

import com.azimsiddiqui.userlisting.di.Constants.API_KEY
import com.azimsiddiqui.userlisting.network.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers(API_KEY)
    suspend fun getUserDetail(userId:String)= apiService.getUserDetail(API_KEY,userId)
}