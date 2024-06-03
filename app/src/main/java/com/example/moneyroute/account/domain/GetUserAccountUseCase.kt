package com.example.moneyroute.account.domain

import com.example.moneyroute.authentication.data.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String) = userRepository.getUserAccount(userId)
}