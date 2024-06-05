package com.example.moneyroute.account.domain

import com.example.moneyroute.authentication.data.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserAccountUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke(userId: String) = usersRepository.getUserAccount(userId)
}