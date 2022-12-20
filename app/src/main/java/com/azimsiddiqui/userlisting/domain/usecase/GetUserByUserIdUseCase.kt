package com.azimsiddiqui.userlisting.domain.usecase

import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import com.azimsiddiqui.userlisting.presentation.ApiResult
import javax.inject.Inject

class GetUserByUserIdUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(userId:String):ApiResult<UserDetailResponse?> = userRepository.getUserDetail(userId)
}