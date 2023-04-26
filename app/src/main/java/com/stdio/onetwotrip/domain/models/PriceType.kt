package com.stdio.onetwotrip.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PriceType(val value: String) : Parcelable {
    @SerializedName("bussiness")
    BUSINESS("bussiness"),

    @SerializedName("economy")
    ECONOMY("economy")
}