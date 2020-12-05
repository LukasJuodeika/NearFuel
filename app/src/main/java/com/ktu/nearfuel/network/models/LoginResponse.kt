package com.ktu.nearfuel.network.models

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)