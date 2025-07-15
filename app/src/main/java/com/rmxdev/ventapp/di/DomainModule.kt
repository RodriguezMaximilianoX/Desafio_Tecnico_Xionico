package com.rmxdev.ventapp.di

import com.rmxdev.ventapp.domain.usecase.GetClientsUseCase
import com.rmxdev.ventapp.domain.usecase.LoginUseCase
import com.rmxdev.ventapp.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { GetClientsUseCase(get()) }
}