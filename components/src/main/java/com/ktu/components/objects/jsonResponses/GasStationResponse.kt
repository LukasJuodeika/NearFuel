package com.ktu.components.objects.jsonResponses

import com.google.gson.annotations.SerializedName
import com.ktu.components.objects.GasStation

data class GasStationRes(
    @SerializedName("data")
    val `data`: List<GasStation>,
    val links: Links,
    val meta: Meta
)


data class Meta(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val prev: Any
)