package com.azimsiddiqui.userlisting.domain.usecase

import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import com.azimsiddiqui.userlisting.presentation.ApiResult
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(): ApiResult<List<User>?> = repository.getUsers()
}