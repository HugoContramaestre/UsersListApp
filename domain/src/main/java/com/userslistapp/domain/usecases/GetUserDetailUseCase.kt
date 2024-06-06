package com.userslistapp.domain.usecases

import com.userslistapp.domain.repository.UserRepository

class GetUserDetailUseCase (private val userRepository: UserRepository) {

    suspend operator fun invoke(id: Int) = userRepository.getUser(id)
}