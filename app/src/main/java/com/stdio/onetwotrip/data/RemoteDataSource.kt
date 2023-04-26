package com.stdio.onetwotrip.data

import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val mainService: MainService) {

    suspend fun getTickets() = mainService.getTickets()

    suspend fun getAirportInfo(code: String) =
        mainService.getAirportInfo("https://airports.myko.info/api/iata", code)
}