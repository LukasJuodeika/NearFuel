package com.ktu.components.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.ktu.components.objects.GasStation
import io.reactivex.Flowable

@Dao
interface GasStationDao {

    @Insert(onConflict = REPLACE)
    fun insertGasStation(item: GasStation)

    @Insert(onConflict = REPLACE)
    fun insertAllGasStations(all: List<GasStation>)

    @Query("SELECT * FROM GasStation WHERE fuelType = :fuelType")
    fun getGasStationsByType(fuelType: String): List<GasStation>

    @Query("DELETE FROM GasStation")
    fun deleteAllData()

    @Query("SELECT * FROM GasStation")
    fun getAllGasStations(): Flowable<List<GasStation>>

    @Update(onConflict = REPLACE)
    fun updateGasStation(data: GasStation)
}