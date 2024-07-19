package com.hillwar.ram.features.personList.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.navigation.NavRoute
import com.hillwar.ram.core.theme.PaddingLarge
import com.hillwar.ram.core.theme.SpacerMedium

@Composable
fun PersonList(
    navController: NavController,
    personList: List<Person>,
    endReached: Boolean,
    loadError: String,
    isLoading: Boolean,
    isSearching: Boolean,
    onLoadMore: () -> Unit
) {
    if (!isLoading && loadError.isNotEmpty()) {
        navController.navigate(NavRoute.Error.route)
    }

    LazyColumn(
        modifier = Modifier.testTag("PersonList"),
        contentPadding = PaddingValues(PaddingLarge)
    ) {
        val itemCount = if (personList.size % EVEN_CHECK == EVEN_RESULT) {
            personList.size / MAX_ELEMENTS_PER_ROW
        } else {
            personList.size / MAX_ELEMENTS_PER_ROW + MIN_ELEMENTS_PER_ROW
        }

        items(itemCount) {
            val validStates = !endReached && !isLoading && !isSearching
            if (it >= itemCount - ITEM_OFFSET && validStates) {
                onLoadMore()
            }
            PersonRow(rowIndex = it, entries = personList, navController = navController)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag("loading_indicator"),
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun PersonRow(
    rowIndex: Int,
    entries: List<Person>,
    navController: NavController
) {
    Column {
        Row {
            PersonEntry(
                person = entries[rowIndex * MAX_ELEMENTS_PER_ROW],
                navController = navController,
                modifier = Modifier.weight(WEIGHT_DEFAULT)
            )
            Spacer(modifier = Modifier.width(SpacerMedium))
            if (entries.size >= rowIndex * MAX_ELEMENTS_PER_ROW + MAX_ELEMENTS_PER_ROW) {
                PersonEntry(
                    person = entries[rowIndex * MAX_ELEMENTS_PER_ROW + MIN_ELEMENTS_PER_ROW],
                    navController = navController,
                    modifier = Modifier.weight(WEIGHT_DEFAULT)
                )
            } else {
                Spacer(modifier = Modifier.weight(WEIGHT_DEFAULT))
            }
        }
        Spacer(modifier = Modifier.height(SpacerMedium))
    }
}
