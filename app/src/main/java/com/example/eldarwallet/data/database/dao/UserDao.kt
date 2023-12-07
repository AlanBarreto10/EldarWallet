package com.example.eldarwallet.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getUser(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()


}