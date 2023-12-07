package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.CardRepository
import javax.inject.Inject

class getUserUseCase  @Inject constructor(
    private val repository: CardRepository
){
    suspend operator fun invoke(): User {
        return repository.getUser()
    }
}