package com.example.eldarwallet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.data.database.entity.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    suspend fun getCards(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: CardEntity)

    @Query("DELETE FROM card_table")
    suspend fun deleteAll()
}