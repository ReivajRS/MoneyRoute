package com.example.moneyroute.authentication.domain

import com.example.moneyroute.authentication.data.User
import com.example.moneyroute.authentication.data.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(user: User) {
        usersRepository.addUser(user)
    }

}