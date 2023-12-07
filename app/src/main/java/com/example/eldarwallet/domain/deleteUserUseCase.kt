package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.CardRepository
import javax.inject.Inject

class deleteUserUseCase @Inject constructor(
    private val repository: CardRepository
) {
    suspend operator fun invoke(){
        repository.deleteUser()
    }

}