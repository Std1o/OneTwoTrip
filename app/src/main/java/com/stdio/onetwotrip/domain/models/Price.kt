package com.stdio.onetwotrip.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(val type: PriceType, val amount: Int) : Parcelable