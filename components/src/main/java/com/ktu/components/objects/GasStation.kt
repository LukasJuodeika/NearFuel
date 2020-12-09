package com.ktu.components.objects

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GasStation(

    var address: String?,
    var created_at: String?,
    var diesel_price: String?,
    var fuelType: String?,
    var fuel_price: String?,
    var gas_price: String?,
    var lat: String,
    val lng: String,
    @PrimaryKey
    val id: Int,
    var title: String?,
    val updated_at: String?,
    val user_id: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(created_at)
        parcel.writeString(diesel_price)
        parcel.writeString(fuelType)
        parcel.writeString(fuel_price)
        parcel.writeString(gas_price)
        parcel.writeString(lat)
        parcel.writeString(lng)
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(updated_at)
        parcel.writeValue(user_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "GasStation(address=$address, created_at=$created_at, diesel_price=$diesel_price, fuelType=$fuelType, fuel_price=$fuel_price, gas_price=$gas_price, lat='$lat', lng='$lng', station_id=$id, title=$title, updated_at=$updated_at, user_id=$user_id)"
    }

    companion object CREATOR : Parcelable.Creator<GasStation> {
        override fun createFromParcel(parcel: Parcel): GasStation {
            return GasStation(parcel)
        }

        override fun newArray(size: Int): Array<GasStation?> {
            return arrayOfNulls(size)
        }
    }
}
