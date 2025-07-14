package com.rmxdev.ventapp.domain.usecase

import com.rmxdev.ventapp.core.PasswordHasher
import com.rmxdev.ventapp.domain.entities.User
import com.rmxdev.ventapp.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(input: String, password: String): Result<User>{
        val user = repository.getUserByEmailOrName(input)
        val hashedInput = PasswordHasher.sha256(password)

        return when {
            user == null -> Result.failure(Exception("Usuario no encontrado"))
            user.password != hashedInput -> Result.failure(Exception("Contraseña incorrecta"))
            else -> Result.success(user)
        }
    }
}