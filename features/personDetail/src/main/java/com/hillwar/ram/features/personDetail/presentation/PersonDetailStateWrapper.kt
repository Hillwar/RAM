package com.hillwar.ram.features.personDetail.presentation

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hillwar.ram.core.navigation.NavRoute

@Composable
fun PersonDetailStateWrapper(
    navController: NavController,
    uiState: PersonDetailUiState,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (uiState) {
        is PersonDetailUiState.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }

        is PersonDetailUiState.Success -> {
            PersonDetailSection(
                person = uiState.person,
                modifier = modifier
            )
        }

        is PersonDetailUiState.Error -> {
            navController.navigate(NavRoute.Error.route)
        }
    }
}
