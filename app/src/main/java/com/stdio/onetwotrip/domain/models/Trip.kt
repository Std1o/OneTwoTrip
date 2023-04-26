package com.stdio.onetwotrip.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trip(val from: String, val to: String) : Parcelable
