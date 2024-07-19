package com.hillwar.ram.features.personList.domain

import com.hillwar.ram.core.extensions.models.PersonListResponse
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.features.personList.data.PersonListRepository
import javax.inject.Inject

interface PersonListInteractor {
    suspend fun getPersonList(page: Int): Request<PersonListResponse>
}

class PersonListInteractorImpl @Inject constructor(
    private val repository: PersonListRepository
) : PersonListInteractor {

    override suspend fun getPersonList(page: Int): Request<PersonListResponse> =
        repository.getPersonList(page)
}
