package com.rmxdev.ventapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientId: Int,
    val productName: String,
    val quantity: Int,
    val unitPrice: Double
)