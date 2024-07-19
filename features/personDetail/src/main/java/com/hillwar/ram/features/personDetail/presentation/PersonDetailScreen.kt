package com.hillwar.ram.features.personDetail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hillwar.ram.core.extensions.ui.DETAIL_TOP_FRACTION
import com.hillwar.ram.core.theme.LightBlue
import com.hillwar.ram.core.theme.PaddingLarge
import com.hillwar.ram.features.personDetail.presentation.components.PersonImage



@Composable
fun PersonDetailScreen(
    personId: Int,
    navController: NavController,
    viewModel: PersonDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = personId) {
        viewModel.getPersonDetail(personId)
    }

    val uiState by remember { viewModel.uiState }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
            .padding(bottom = PaddingLarge)
    ) {
        PersonDetailTopSection(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(DETAIL_TOP_FRACTION)
                .align(Alignment.TopCenter)
        )
        PersonDetailStateWrapper(
            navController = navController,
            uiState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = PersonDetailScreenDefaults.TOP_PADDING + PersonDetailScreenDefaults.PERSON_IMAGE_SIZE / PersonDetailScreenDefaults.OFFSET_DIVIDER,
                    start = PaddingLarge,
                    end = PaddingLarge,
                    bottom = PaddingLarge
                )
                .shadow(
                    PersonDetailScreenDefaults.SHADOW_ELEVATION,
                    PersonDetailScreenDefaults.ROUNDED_CORNER_SHAPE
                )
                .clip(PersonDetailScreenDefaults.ROUNDED_CORNER_SHAPE)
                .background(MaterialTheme.colors.surface)
                .padding(PaddingLarge)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(PersonDetailScreenDefaults.LOADING_SIZE)
                .align(Alignment.Center)
                .padding(PaddingLarge)
        )
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (uiState is PersonDetailUiState.Success) {
                PersonImage((uiState as PersonDetailUiState.Success).person)
            }
        }
    }
}

object PersonDetailScreenDefaults {
    val TOP_PADDING = 85.dp
    val PERSON_IMAGE_SIZE = 250.dp
    val OFFSET_DIVIDER = 2f
    val SHADOW_ELEVATION = 5.dp
    val ROUNDED_CORNER_SHAPE = RoundedCornerShape(10.dp)
    val LOADING_SIZE = 100.dp
}
