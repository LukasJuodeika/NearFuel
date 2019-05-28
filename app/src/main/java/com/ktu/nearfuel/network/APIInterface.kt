package com.ktu.nearfuel.network

import com.ktu.components.objects.GasStation
import com.ktu.components.objects.NearestGasStation.NearestGasStationsJsonResponse
import com.ktu.components.objects.jsonResponses.gasStationResponse.GasStationResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

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
        @Query("location") location: String,
        @Query("uid") uid: String
    ): Observable<GasStationResponse>

    @PUT("station")
    fun updateStation(
        @Part gasStationRequest: GasStation,
        @Part uid:String
    ): Observable<GasStation>

    @POST("user")
    fun loginUser(
        @Part email: String,
        @Part uid:String
    ): Observable<ResponseBody>
}


