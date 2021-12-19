package com.azimsiddiqui.userlisting.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.data.model.UserListResponse
import com.azimsiddiqui.userlisting.domain.usecase.GetAllUsersUseCase
import com.azimsiddiqui.userlisting.domain.usecase.GetUserByUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val allUsersUseCase: GetAllUsersUseCase,
    private val userByUserIdUseCase: GetUserByUserIdUseCase
) : ViewModel() {

    private var _userListLiveData = MutableLiveData<List<User>?>()
    val userListLiveData: LiveData<List<User>?>
        get() = _userListLiveData

    private var _userDetailsLiveData = MutableLiveData<UserDetailResponse?>()
    val userDetailsLiveData: LiveData<UserDetailResponse?>
        get() = _userDetailsLiveData

    fun getUserList() {
        viewModelScope.launch {
            val userList = allUsersUseCase.execute()
            _userListLiveData.value = userList
//            if (response.isSuccessful) {
//                _userListLiveData.value = response.body() as UserListResponse
//            } else {
//                Log.i("error", "getUserList: ${response.message()} ")
//            }
        }
    }

    fun getUserDetail(id:String){
        viewModelScope.launch {
            val userDetail = userByUserIdUseCase.execute(id)
            _userDetailsLiveData.value = userDetail
//            if(response.isSuccessful){
//                _userDetailsLiveData.value=response.body() as UserDetailResponse
//            }
//            else{
//                Log.i("error", "userDetail: ${response.message()} ")
//            }
        }
    }

}