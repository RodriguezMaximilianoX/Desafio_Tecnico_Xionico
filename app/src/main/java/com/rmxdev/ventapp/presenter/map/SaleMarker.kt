package com.rmxdev.ventapp.presenter.map

data class SaleMarker(
    val latitude: Double,
    val longitude: Double,
    val clientName: String,
    val sellerName: String,
    val amount: Double,
    val quantity: Int
)
