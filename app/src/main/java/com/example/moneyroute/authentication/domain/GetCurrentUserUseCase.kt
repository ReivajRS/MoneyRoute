package com.example.moneyroute.authentication.domain

import com.example.moneyroute.authentication.data.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentUserUseCase @Inject constructor(
    userRepository: UserRepository
) {
    val userId: String = userRepository.getUserId() ?: ""
    operator fun invoke() = userId
}