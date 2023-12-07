package com.example.eldarwallet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eldarwallet.data.database.dao.CardDao
import com.example.eldarwallet.data.database.dao.UserDao
import com.example.eldarwallet.data.database.entity.CardEntity
import com.example.eldarwallet.data.database.entity.UserEntity

@Database(entities = [CardEntity::class, UserEntity::class], version = 2)
abstract class CardDatabase: RoomDatabase() {

    abstract fun getCardDao(): CardDao
    abstract fun getUserDao(): UserDao
}