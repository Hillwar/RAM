package com.hillwar.ram.features.personList.data

import com.hillwar.ram.core.extensions.models.PersonListResponse
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.core.network.RequestHandler
import com.hillwar.ram.core.network.RickAndMortyService
import javax.inject.Inject

interface PersonListRepository {
    suspend fun getPersonList(page: Int): Request<PersonListResponse>
}

class PersonListRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService,
    private val requestHandler: RequestHandler
) : PersonListRepository {

    override suspend fun getPersonList(page: Int): Request<PersonListResponse> =
        requestHandler.handleRequest { service.getPersonList(page) }
}