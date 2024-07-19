package com.hillwar.ram.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hillwar.ram.core.navigation.NavRoute
import com.hillwar.ram.core.theme.RAMTheme
import com.hillwar.ram.features.personDetail.presentation.PersonDetailScreen
import com.hillwar.ram.features.personList.presentation.PersonListScreen
import com.hillwar.ram.features.error.ErrorScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RAMTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavRoute.PersonList.route
                ) {
                    composable(NavRoute.PersonList.route) {
                        PersonListScreen(navController = navController)
                    }
                    composable(
                        route = NavRoute.PersonDetail.route,
                        arguments = listOf(
                            navArgument(NavRoute.PERSON_ID) {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val personId = remember {
                            it.arguments?.getInt(NavRoute.PERSON_ID) ?: DEFAULT_PERSON_ID
                        }

                        PersonDetailScreen(
                            personId = personId,
                            navController = navController
                        )
                    }
                    composable(NavRoute.Error.route) {
                        ErrorScreen(navController = navController)
                    }
                }
            }
        }
    }

    companion object {
        const val DEFAULT_PERSON_ID = 1
    }
}
