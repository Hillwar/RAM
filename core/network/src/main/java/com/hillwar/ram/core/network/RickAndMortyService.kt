package com.hillwar.ram.core.network

import com.hillwar.ram.core.extensions.models.PersonListResponse
import com.hillwar.ram.core.extensions.models.Person
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET(PERSON_DETAIL_PATH)
    suspend fun getPersonDetail(
        @Path(PERSON_ID) personId: Int
    ): Response<Person>

    @GET(PERSON_LIST_PATH)
    suspend fun getPersonList(
        @Query(PARAMETER_PAGE) page: Int
    ): Response<PersonListResponse>

    companion object {
        const val PERSON_LIST_PATH = "character"
        const val PARAMETER_PAGE = "page"
        const val PERSON_ID = "characterId"
        const val PERSON_DETAIL_PATH = "character/{$PERSON_ID}"
    }
}
