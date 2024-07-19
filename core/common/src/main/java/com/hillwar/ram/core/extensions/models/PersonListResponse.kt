package com.hillwar.ram.core.extensions.models

data class PersonListResponse(
    val info: PersonListInfo,
    val results: List<Person>
)
