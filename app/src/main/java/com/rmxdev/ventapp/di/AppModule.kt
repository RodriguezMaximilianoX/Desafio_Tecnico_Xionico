package com.rmxdev.ventapp.di

import androidx.room.Room
import com.rmxdev.ventapp.data.database.AppDatabase
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database-ventapp"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().clientDao() }
    single { get<AppDatabase>().articleDao() }
    single { get<AppDatabase>().saleDao() }
    single { get<AppDatabase>().invoiceDao() }

}