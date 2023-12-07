package com.example.eldarwallet.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "cardnumber") val cardNumber: String,
    @ColumnInfo(name = "cardtitular") val cardTitular: String,
    @ColumnInfo(name = "cardmonth") val cardMonth: String,
    @ColumnInfo(name = "cardyear") val cardYear: String,
    @ColumnInfo(name = "codSecuruty") val codSecuruty: String,
)
