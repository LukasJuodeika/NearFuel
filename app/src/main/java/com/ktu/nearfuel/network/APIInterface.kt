package com.ktu.nearfuel.network

import io.reactivex.Observable
import org.w3c.dom.DocumentType
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("document/list")
    abstract fun getDocumentsTypes(
        @Query("authToken") auhToken: String
    ): Call<List<DocumentType>>

}
