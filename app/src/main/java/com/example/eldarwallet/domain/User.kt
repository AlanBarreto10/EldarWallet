package com.example.eldarwallet.domain

import com.example.eldarwallet.data.database.entity.CardEntity
import com.example.eldarwallet.data.database.entity.UserEntity

data class User(
    val id: Int = 0,
    val email: String,
    val nombre: String,
    val apellido: String,
    val password: String
)

fun UserEntity.toDomain() = User(id, email, nombre, apellido, contrasena)
