package com.example.eldarwallet.domain

import com.example.eldarwallet.data.network.AuthenticationRepository
import com.example.eldarwallet.data.network.LoginResult
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationRepository.login(email, password)

}