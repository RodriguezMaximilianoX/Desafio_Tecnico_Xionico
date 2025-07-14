package com.rmxdev.ventapp.domain.usecase

import com.rmxdev.ventapp.core.PasswordHasher
import com.rmxdev.ventapp.domain.entities.User
import com.rmxdev.ventapp.domain.repository.UserRepository

class RegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, name: String, password: String, confirmPassword: String): Result<Unit> {
        if (password != confirmPassword) {
            return Result.failure(Exception("Las contraseñas no coinciden"))
        }
        val existingUser = userRepository.getUserByEmailOrName(email)
        if (existingUser != null) {
            return Result.failure(Exception("El usuario ya existe"))
        }

        val hashed = PasswordHasher.sha256(password)

        val newUser = User(
            email = email,
            name = name,
            password = hashed
        )

        userRepository.insertUser(newUser)

        return Result.success(Unit)

    }
}