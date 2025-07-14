package com.rmxdev.ventapp.domain.repository

import com.rmxdev.ventapp.domain.entities.User

interface UserRepository {
    suspend fun insertUser(user: User)
    suspend fun getUserByEmail(email: String): User?
}