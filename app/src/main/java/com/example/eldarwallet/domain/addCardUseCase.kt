package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.CardRepository
import javax.inject.Inject

class addCardUseCase @Inject constructor(
    private val repository: CardRepository
) {
    suspend operator fun invoke(card: Card){
        repository.addCard(card)
    }
}