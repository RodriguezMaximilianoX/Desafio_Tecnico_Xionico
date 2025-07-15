package com.rmxdev.ventapp.di

import com.rmxdev.ventapp.presenter.clients.ClientViewModel
import com.rmxdev.ventapp.presenter.login.LoginViewModel
import com.rmxdev.ventapp.presenter.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { ClientViewModel(get(), get()) }
}
