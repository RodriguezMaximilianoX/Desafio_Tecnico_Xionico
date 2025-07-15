package com.rmxdev.ventapp.di

import com.rmxdev.ventapp.presenter.clients.ClientViewModel
import com.rmxdev.ventapp.presenter.login.LoginViewModel
import com.rmxdev.ventapp.presenter.map.MapsViewModel
import com.rmxdev.ventapp.presenter.register.RegisterViewModel
import com.rmxdev.ventapp.presenter.reports.SalesReportViewModel
import com.rmxdev.ventapp.presenter.sales.SalesViewModel
import com.rmxdev.ventapp.presenter.share.ShareViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { ClientViewModel(get(), get()) }
    viewModel { SalesViewModel(get(), get(), get(), get()) }
    viewModel { SalesReportViewModel(get()) }
    viewModel { MapsViewModel(get(), get()) }
    viewModel { ShareViewModel(get(), get(), get()) }
}
