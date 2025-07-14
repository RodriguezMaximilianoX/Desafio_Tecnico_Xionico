package com.rmxdev.ventapp.di

import com.rmxdev.ventapp.domain.usecase.LoginUseCase
import com.rmxdev.ventapp.domain.usecase.RegisterUseCase
import com.rmxdev.ventapp.presenter.register.RegisterScreen
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
}