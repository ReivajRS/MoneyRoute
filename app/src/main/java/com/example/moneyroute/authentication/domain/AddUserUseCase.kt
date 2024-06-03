package com.example.moneyroute.authentication.domain

import com.example.moneyroute.authentication.data.User
import com.example.moneyroute.authentication.data.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.addUser(user)
    }

}