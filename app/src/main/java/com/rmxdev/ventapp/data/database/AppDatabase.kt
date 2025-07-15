package com.rmxdev.ventapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmxdev.ventapp.data.dao.ArticleDao
import com.rmxdev.ventapp.data.dao.ClientDao
import com.rmxdev.ventapp.data.dao.UserDao
import com.rmxdev.ventapp.domain.entities.Article
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.entities.User

@Database(entities = [User::class, Client::class, Article::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun clientDao(): ClientDao
    abstract fun articleDao(): ArticleDao
}