package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.CardRepository
import javax.inject.Inject

class getCardsUseCase @Inject constructor(
    private val repository: CardRepository
){
    suspend operator fun invoke(): List<Card> {
        return repository.getAllCardsFromDatabase()
    }
}