package com.ktu.components.objects.jsonResponses.gasStationResponse

import com.google.gson.annotations.SerializedName
import com.ktu.components.objects.GasStation

data class GasStationResponse(
    @SerializedName("data")
    val `data`: List<GasStation>,
    val links: Links,
    val meta: Meta
)