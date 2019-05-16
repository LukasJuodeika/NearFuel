package com.ktu.components.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ktu.components.objects.GasStation

@Database(
    entities =
    [
        GasStation::class
    ],
    version = 2,
    exportSchema = false

)
abstract class Database : RoomDatabase()
{
    abstract fun gasStationDao(): GasStationDao

}