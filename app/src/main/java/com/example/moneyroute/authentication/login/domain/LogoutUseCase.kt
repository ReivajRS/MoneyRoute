package com.example.moneyroute.authentication.login.domain

import com.example.moneyroute.authentication.data.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke() = usersRepository.signOut()

}