package com.rmxdev.ventapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoices")
data class Invoice(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientId: Int,
    val clientName: String,
    val sellerName: String,
    val totalAmount: Double,
    val totalProducts: Int,
    val timestamp: Long = System.currentTimeMillis()
)
