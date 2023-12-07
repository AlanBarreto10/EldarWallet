package com.example.eldarwallet.domain

import android.util.Log
import com.example.eldarwallet.data.network.AuthenticationRepository
import com.example.eldarwallet.data.network.UserRepository
import javax.inject.Inject

class RegisterUseCase  @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val accountCreated = authenticationRepository.createAccount(user.email, user.password) != null  //se considera que se ha creado la cuenta
        return if (accountCreated) {
            userRepository.createUserTableInFb(user)
        } else {
            false
        }
    }
}