package com.stdio.onetwotrip.data

import com.stdio.onetwotrip.domain.models.Ticket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET("ott/search")
    suspend fun getTickets(): Response<List<Ticket>>
}