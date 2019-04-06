package com.ktu.components.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GasStation() {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var title: String = ""

    var price: String = ""

    var fuelType: String = ""

    var lng: String = ""

    var lat: String = ""

    var distance: Double = 0.0

    constructor(
        title: String,
        price: String,
        fuelType: String,
        lng: String,
        lat: String,
        distance: Double
    ) : this() {

        this.title = title
        this.price = price
        this.fuelType = fuelType
        this.lng = lng
        this.lat = lat
        this.distance = distance
    }
}
