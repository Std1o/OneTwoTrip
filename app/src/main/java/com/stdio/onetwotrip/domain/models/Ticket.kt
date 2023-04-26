package com.stdio.onetwotrip.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(val currency: String, val prices: List<Price>, val trips: List<Trip>) : Parcelable
