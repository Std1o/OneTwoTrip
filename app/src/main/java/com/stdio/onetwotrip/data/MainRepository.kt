package com.stdio.onetwotrip.data

import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : BaseRepository() {

    suspend fun getTickets() =
        flow { emit(apiCall { remoteDataSource.getTickets() }) }

    suspend fun getAirportInfo(code: String) =
        flow { emit(apiCall { remoteDataSource.getAirportInfo(code) }) }
}