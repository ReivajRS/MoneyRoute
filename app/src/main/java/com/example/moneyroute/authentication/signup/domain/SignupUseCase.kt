package com.example.moneyroute.authentication.signup.domain

import com.example.moneyroute.authentication.data.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignupUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.createUserWithEmailAndPassword(email, password)
}