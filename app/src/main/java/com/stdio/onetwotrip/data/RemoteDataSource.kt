package com.stdio.onetwotrip.data

import com.stdio.onetwotrip.data.MainService
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val mainService: MainService) {

    suspend fun getTickets() = mainService.getTickets()
}