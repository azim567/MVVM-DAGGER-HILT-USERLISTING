package com.azimsiddiqui.userlisting.domain.usecase

import com.azimsiddiqui.userlisting.data.model.User
import com.azimsiddiqui.userlisting.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(): List<User>? = repository.getUsers()
}