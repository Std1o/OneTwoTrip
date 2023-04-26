package com.stdio.onetwotrip.data

import com.stdio.onetwotrip.domain.models.AirportInfoResponse
import com.stdio.onetwotrip.domain.models.Ticket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MainService {

    @GET("ott/search")
    suspend fun getTickets(): Response<List<Ticket>>

    @GET
    suspend fun getAirportInfo(@Url url: String, @Query("code") code: String): Response<AirportInfoResponse>
}