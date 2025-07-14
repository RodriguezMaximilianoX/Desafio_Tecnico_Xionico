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
        ).build()
    }
}