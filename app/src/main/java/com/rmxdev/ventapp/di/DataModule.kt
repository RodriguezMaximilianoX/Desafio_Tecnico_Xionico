package com.rmxdev.ventapp.di

import com.rmxdev.ventapp.data.repositoryImpl.ClientRepositoryImpl
import com.rmxdev.ventapp.data.repositoryImpl.UserRepositoryImpl
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ClientRepository> { ClientRepositoryImpl(get()) }
}