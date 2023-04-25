package com.stdio.onetwotrip.domain.models

import com.google.gson.annotations.SerializedName

enum class PriceType(val value: String) {
    @SerializedName("bussiness")
    BUSINESS("bussiness"),

    @SerializedName("economy")
    ECONOMY("economy")
}