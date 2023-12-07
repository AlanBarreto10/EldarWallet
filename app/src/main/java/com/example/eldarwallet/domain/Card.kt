package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.entity.CardEntity

data class Card(
    val id: Int = System.currentTimeMillis().hashCode(),
    val cardNumber: String,
    val cardTitular: String,
    val cardMonth: String,
    val cardYear: String,
    val codSecuruty: String,
)

fun CardEntity.toDomain() = Card(id,cardNumber,cardTitular,cardMonth,cardYear,codSecuruty)
