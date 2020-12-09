package com.ktu.nearfuel.network

import com.ktu.components.objects.GasStation
import com.ktu.nearfuel.network.models.LoginResponse
import com.ktu.nearfuel.network.models.RegistrationRequestBody
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface APIInterface {

    @GET("api/station")
    fun getAllGasStations(): Single<List<GasStation>>

    @PUT("api/station/{id}")
    fun updateStation(@Path("id") stationId: Int, @Body gasStation: GasStation): Completable

    @POST("api/station/{stationId}/price")
    fun addPrice(@Path("stationId") stationId: Int, @Body price: Price): Completable

    @POST("oauth/token")
    @FormUrlEncoded
    fun login(
        @Header("Authorization") authorization: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password"
    ) : Single<LoginResponse>

    @POST("register")
    fun register(@Body registrationBody: RegistrationRequestBody): Completable

    @GET("api/station/{id}/price")
    fun getPrices(@Path("id") stationId: Int): Single<List<Price>>
}


