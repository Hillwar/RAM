package com.hillwar.ram.core.navigation

sealed class NavRoute(val route: String) {
    data object PersonList : NavRoute("person_list_screen")

    data object PersonDetail : NavRoute("$PERSON_DETAIL/{$PERSON_ID}") {
        fun createRoute(personId: Int) = "person_detail_screen/$personId"
    }

    data object Error : NavRoute("error_screen")

    companion object {
        const val PERSON_DETAIL= "person_detail_screen"
        const val PERSON_ID= "personId"
    }
}
