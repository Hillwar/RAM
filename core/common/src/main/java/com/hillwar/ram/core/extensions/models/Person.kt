package com.hillwar.ram.core.extensions.models

data class Person(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val location: PersonLocation,
    val episode: List<String>
)
