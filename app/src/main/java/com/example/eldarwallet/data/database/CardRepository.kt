package com.example.eldarwallet.data.database


import com.example.eldarwallet.data.database.dao.CardDao
import com.example.eldarwallet.data.database.dao.UserDao
import com.example.eldarwallet.data.database.entity.CardEntity
import com.example.eldarwallet.data.database.entity.UserEntity
import com.example.eldarwallet.domain.Card
import com.example.eldarwallet.domain.toDomain
import com.example.eldarwallet.domain.User
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardDao: CardDao,
    private val userDao: UserDao,
) {
    suspend fun getAllCardsFromDatabase():List<Card>{
        val response: List<CardEntity> = cardDao.getCards()
        return response.map { it.toDomain() }
    }

    suspend fun addCard(card: Card){
        cardDao.insert(CardEntity(card.id, card.cardNumber, card.cardTitular, card.cardMonth, card.cardYear, card.codSecuruty))
    }

    /* Obtengo siempre el usuario de la base de datos remota */

    suspend fun getUser(): User {
        val response: UserEntity = userDao.getUser()
        return response.toDomain()
    }

    suspend fun addUser(user: User){
        userDao.insert(UserEntity(user.id, user.email, user.nombre, user.apellido, user.password))
    }

    suspend fun deleteUser() {
        userDao.deleteAll()
    }

    suspend fun deleteCards() {
        cardDao.deleteAll()
    }

}