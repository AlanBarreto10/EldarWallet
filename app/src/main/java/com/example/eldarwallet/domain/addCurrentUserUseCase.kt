package com.example.eldarwallet.domain


import com.example.eldarwallet.data.network.UserRepository
import javax.inject.Inject

class getCurrentUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): User?{
        return userRepository.getCurrentUserFromFirestore()
    }
}