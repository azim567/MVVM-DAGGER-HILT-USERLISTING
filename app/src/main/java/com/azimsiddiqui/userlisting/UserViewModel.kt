package com.azimsiddiqui.userlisting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azimsiddiqui.userlisting.data.UserDetailResponse
import com.azimsiddiqui.userlisting.data.UserListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private var _userListLiveData = MutableLiveData<UserListResponse>()
    val userListLiveData: LiveData<UserListResponse>
        get() = _userListLiveData

    private var _userDetailsLiveData = MutableLiveData<UserDetailResponse>()
    val userDetailsLiveData: LiveData<UserDetailResponse>
        get() = _userDetailsLiveData

    fun getUserList() {
        viewModelScope.launch {
            val response = userRepository.getUsers()
            if (response.isSuccessful) {
                _userListLiveData.value = response.body() as UserListResponse
            } else {
                Log.i("error", "getUserList: ${response.message()} ")
            }
        }
    }

    fun getUserDetail(id:String){
        viewModelScope.launch {
            val response = userRepository.getUserDetail(id)
            if(response.isSuccessful){
                _userDetailsLiveData.value=response.body() as UserDetailResponse
            }
            else{
                Log.i("error", "userDetail: ${response.message()} ")
            }
        }
    }

}