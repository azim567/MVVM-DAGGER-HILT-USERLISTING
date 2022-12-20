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
import com.azimsiddiqui.userlisting.presentation.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val allUsersUseCase: GetAllUsersUseCase,
    private val userByUserIdUseCase: GetUserByUserIdUseCase,
) : ViewModel() {

    private var _userListLiveData = MutableLiveData<ApiResult<List<User>?>>()
    val userListLiveData: LiveData<ApiResult<List<User>?>>
        get() = _userListLiveData

    private var _userDetailsLiveData = MutableLiveData<ApiResult<UserDetailResponse?>>()
    val userDetailsLiveData: LiveData<ApiResult<UserDetailResponse?>>
        get() = _userDetailsLiveData

    fun getUserList() {
        viewModelScope.launch {
            _userListLiveData.value = ApiResult.Loading()
            try {
                val apiResult = allUsersUseCase.execute()
                _userListLiveData.value = apiResult
            } catch (e: Exception) {
                _userListLiveData.value = ApiResult.Error(e.message)
            }
        }
    }

    fun getUserDetail(id: String) {
        viewModelScope.launch {
            _userListLiveData.value = ApiResult.Loading()
            try {
                val userDetail = userByUserIdUseCase.execute(id)
                _userDetailsLiveData.value = userDetail
            } catch (e: Exception) {
                _userListLiveData.value = ApiResult.Error(e.message)
            }

        }
    }

}