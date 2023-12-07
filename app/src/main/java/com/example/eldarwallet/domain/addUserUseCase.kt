package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.CardRepository
import javax.inject.Inject

class addUserUseCase @Inject constructor(
    private val repository: CardRepository
) {
    suspend operator fun invoke(user: User){
        repository.addUser(user)
    }
}