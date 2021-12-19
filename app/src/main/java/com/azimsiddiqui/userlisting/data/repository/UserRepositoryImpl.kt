package com.azimsiddiqui.userlisting.data.repository

import android.util.Log
import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.data.repository.datasource.UserRemoteDataSource
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource):
    UserRepository {


    override suspend fun getUsers(): List<User>? {
        lateinit var userList : List<User>
        try {
            val response=userRemoteDataSource.getUsers()
            if(response.isSuccessful){
                response.body()?.let {
                    userList = it.data
                }
            }
        }catch (e:Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return userList
    }

    override suspend fun getUserDetail(userId: String): UserDetailResponse? {
        var userDetail : UserDetailResponse? = null
        try {
            val response=userRemoteDataSource.getUserDetail(userId)
            if(response.isSuccessful){
                response.body()?.let {
                    userDetail = it
                }
            }
        }catch (e:Exception){
            Log.i("MyTag",e.message.toString())
        }
        return userDetail
    }
}