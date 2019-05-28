package com.ktu.nearfuel.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktu.components.objects.GasStation

class GasStationRequestBody(
    var gasStation:GasStation,
    val uid: String
)
