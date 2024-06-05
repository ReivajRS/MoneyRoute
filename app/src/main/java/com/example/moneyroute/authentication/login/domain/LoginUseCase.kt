package com.example.moneyroute.authentication.login.domain

import com.example.moneyroute.authentication.data.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(email: String, password: String) =
        usersRepository.signInWithEmailAndPassword(email, password)
}