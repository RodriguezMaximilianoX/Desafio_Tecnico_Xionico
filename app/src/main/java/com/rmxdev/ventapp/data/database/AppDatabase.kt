package com.rmxdev.ventapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmxdev.ventapp.data.dao.ArticleDao
import com.rmxdev.ventapp.data.dao.ClientDao
import com.rmxdev.ventapp.data.dao.InvoiceDao
import com.rmxdev.ventapp.data.dao.SaleDao
import com.rmxdev.ventapp.data.dao.UserDao
import com.rmxdev.ventapp.domain.entities.Article
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.entities.Invoice
import com.rmxdev.ventapp.domain.entities.Sale
import com.rmxdev.ventapp.domain.entities.User

@Database(
    entities = [User::class, Client::class, Article::class, Sale::class, Invoice::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun clientDao(): ClientDao
    abstract fun articleDao(): ArticleDao
    abstract fun saleDao(): SaleDao
    abstract fun invoiceDao(): InvoiceDao
}