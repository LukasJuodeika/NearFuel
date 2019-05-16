package com.ktu.nearfuel.network

import com.ktu.components.objects.NearestGasStation.NearestGasStationsJsonResponse
import com.ktu.components.objects.jsonResponses.gasStationResponse.GasStationResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("place/nearbysearch/json")
    fun getNearestGasStations(
        @Query("location") location: String,
        @Query("rankby") rankyBy: String,
        @Query("type") type: String,
        @Query("key") key: String
    ): Observable<NearestGasStationsJsonResponse>

    @GET("stations")
    fun getAllGasStations(
    ): Observable<GasStationResponse>
}


