package com.rmxdev.ventapp.data.repositoryImpl

import com.rmxdev.ventapp.data.dao.UserDao
import com.rmxdev.ventapp.domain.entities.User
import com.rmxdev.ventapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}