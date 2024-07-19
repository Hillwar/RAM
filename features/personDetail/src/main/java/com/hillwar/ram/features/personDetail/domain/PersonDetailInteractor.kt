package com.hillwar.ram.features.personDetail.domain

import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.features.personDetail.data.PersonDetailRepository
import javax.inject.Inject

interface PersonDetailInteractor {
    suspend fun getPersonDetail(personId: Int): Request<Person>
}

class PersonDetailInteractorImpl @Inject constructor(
    private val repository: PersonDetailRepository
) : PersonDetailInteractor {
    override suspend fun getPersonDetail(personId: Int): Request<Person> =
        repository.getPersonDetail(personId)
}
