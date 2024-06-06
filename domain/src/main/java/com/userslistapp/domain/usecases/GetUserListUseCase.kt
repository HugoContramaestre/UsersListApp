package com.userslistapp.domain.usecases

import com.userslistapp.domain.repository.UserRepository

class GetUserListUseCase (private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.getUserList()
}