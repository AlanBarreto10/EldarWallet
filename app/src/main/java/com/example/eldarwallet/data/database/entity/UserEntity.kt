package com.example.eldarwallet.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellido") val apellido: String,
    @ColumnInfo(name = "contrasena") val contrasena: String,
)

