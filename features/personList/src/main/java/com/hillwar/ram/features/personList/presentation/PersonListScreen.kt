package com.hillwar.ram.features.personList.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hillwar.ram.core.extensions.ui.DETAIL_TOP_FRACTION
import com.hillwar.ram.core.theme.DarkBlue
import com.hillwar.ram.core.theme.LightBlue
import com.hillwar.ram.core.theme.PaddingLarge
import com.hillwar.ram.core.theme.SpacerLarge
import com.hillwar.ram.core.theme.SpacerSmall
import com.hillwar.ram.features.personList.R

const val EMPTY = ""
const val MAX_LINES = 1
const val WEIGHT_DEFAULT = 1f
const val MAX_ELEMENTS_PER_ROW = 2
const val MIN_ELEMENTS_PER_ROW = 1
const val EVEN_CHECK = 2
const val EVEN_RESULT = 0
const val ITEM_OFFSET = 1

@Composable
fun PersonListScreen(
    navController: NavController,
    viewModel: PersonListViewModel = hiltViewModel()
) {

    val onReturnToScreenState by rememberUpdatedState(viewModel::loadPersons)

    LaunchedEffect(Unit) {
        onReturnToScreenState()
    }

    val personList by remember { viewModel.personList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val isSearching by remember { viewModel.isSearching }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue)
            .padding(bottom = PaddingLarge)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(DETAIL_TOP_FRACTION)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            DarkBlue,
                            Color.Transparent
                        )
                    ),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                )
        )
        Column {
            Spacer(modifier = Modifier.height(SpacerLarge))
            Image(
                painter = painterResource(R.drawable.rick_and_morty),
                contentDescription = stringResource(R.string.person_list_logo_content_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(SpacerSmall))
            SearchBar(
                hint = stringResource(R.string.person_list_search_hint),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingLarge)
            ) {
                viewModel.searchPerson(it)
            }
            Spacer(modifier = Modifier.height(SpacerSmall))
            PersonList(
                navController = navController,
                personList = personList,
                endReached = endReached,
                loadError = loadError,
                isLoading = isLoading,
                isSearching = isSearching,
                onLoadMore = { viewModel.loadPersons() }
            )
        }
    }
}