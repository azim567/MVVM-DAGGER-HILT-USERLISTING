package com.azimsiddiqui.userlisting.domain.usecase

import com.azimsiddiqui.userlisting.data.model.UserDetailResponse
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByUserIdUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(userId:String):UserDetailResponse? = userRepository.getUserDetail(userId)
}