package com.example.moneyroute.authentication.domain

import com.example.moneyroute.authentication.data.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke() = usersRepository.getUserId() ?: ""
}