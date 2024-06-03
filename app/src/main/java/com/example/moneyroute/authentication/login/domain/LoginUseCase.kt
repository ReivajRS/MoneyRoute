package com.example.moneyroute.authentication.login.domain

import com.example.moneyroute.authentication.data.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String) =
        userRepository.signInWithEmailAndPassword(email, password)
}