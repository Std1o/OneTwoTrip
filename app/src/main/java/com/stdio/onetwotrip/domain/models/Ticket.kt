package com.stdio.onetwotrip.domain.models

data class Ticket(val currency: String, val prices: List<Price>, val trips: List<Trip>)
