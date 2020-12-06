package com.ktu.nearfuel.network

import com.ktu.components.objects.GasStation
import com.ktu.components.objects.NearestGasStation.NearestGasStationsJsonResponse
import com.ktu.components.objects.jsonResponses.gasStationResponse.GasStationResponse
import com.ktu.nearfuel.network.models.GasStationRequestBody
import com.ktu.nearfuel.network.models.LoginRequestBody
import com.ktu.nearfuel.network.models.LoginResponse
import com.ktu.nearfuel.network.models.RegistrationRequestBody
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
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

    @GET("api/station")
    fun getAllGasStations(): Observable<GasStationResponse>

    @PUT("station")
    fun updateStation(
        @Body gasStationRequest: GasStationRequestBody

    ): Observable<GasStation>

    @POST("user")
    fun loginUser(
        @Body loginRequest: LoginRequestBody
    ): Observable<ResponseBody>

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
}


