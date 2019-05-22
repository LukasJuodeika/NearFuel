package com.ktu.components.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GasStation(
    val address: String?,
    val created_at: String?,
    val diesel_price: String?,
    val fuelType: String?,
    val fuel_price: String?,
    val gas_price: String?,
    val lat: String,
    val lng: String,
    @PrimaryKey
    val station_id: Int?,
    val title: String?,
    val updated_at: String?,
    val user_id: Int?
)
