package com.rmxdev.ventapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rmxdev.ventapp.domain.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE email = :input OR name = :input")
    suspend fun getUserByEmailOrName(input: String): User?
}