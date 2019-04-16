package com.ktu.nearfuel.network

import com.google.gson.JsonElement
import com.ktu.components.objects.GasStation
import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkApi {

    @GET(Endpoints.posts)
    fun getAllPosts(): Observable<List<GasStation>>
}