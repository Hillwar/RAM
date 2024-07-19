package com.hillwar.ram.features.personDetail.data

import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.core.network.RequestHandler
import com.hillwar.ram.core.network.RickAndMortyService
import javax.inject.Inject

interface PersonDetailRepository {
    suspend fun getPersonDetail(personId: Int): Request<Person>
}

class PersonDetailRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService,
    private val requestHandler: RequestHandler
) : PersonDetailRepository {
    override suspend fun getPersonDetail(personId: Int): Request<Person> =
        requestHandler.handleRequest { service.getPersonDetail(personId) }
}
