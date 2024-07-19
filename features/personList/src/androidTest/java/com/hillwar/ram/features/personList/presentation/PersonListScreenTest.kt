package com.hillwar.ram.features.personList.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.extensions.models.PersonListInfo
import com.hillwar.ram.core.extensions.models.PersonListResponse
import com.hillwar.ram.core.extensions.models.PersonLocation
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.core.theme.RAMTheme
import com.hillwar.ram.features.personList.domain.PersonListInteractor
import org.junit.Rule
import org.junit.Test

class PersonListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPersonListScreen() {
        val personList = mutableStateOf(emptyList<Person>())
        val loadError = mutableStateOf("")
        val isLoading = mutableStateOf(false)
        val isSearching = mutableStateOf(false)
        val endReached = mutableStateOf(false)

        val viewModel = object : PersonListViewModel(MockPersonListInteractor()) {
            init {
                this.personList = personList
                this.loadError = loadError
                this.isLoading = isLoading
                this.isSearching = isSearching
                this.endReached = endReached
            }
        }

        composeTestRule.setContent {
            RAMTheme {
                PersonListScreen(
                    navController = rememberNavController(),
                    viewModel = viewModel
                )
            }
        }

        isLoading.value = true
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()

        personList.value = listOf(
            Person(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                image = "",
                location = PersonLocation("Earth"),
                episode = listOf()
            ),
            Person(
                id = 2,
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                image = "",
                location = PersonLocation("Earth"),
                episode = listOf()
            )
        )
        isLoading.value = false
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Morty Smith").assertIsDisplayed()

        personList.value = List(20) {
            Person(
                id = it,
                name = "Person $it",
                status = "Alive",
                species = "Human",
                image = "",
                location = PersonLocation("Location $it"),
                episode = listOf()
            )
        }
        composeTestRule.onNodeWithTag("PersonList").performScrollToIndex(7)
        composeTestRule.onNodeWithTag("PersonList").performScrollToIndex(7)
        composeTestRule.onNodeWithTag("PersonList").performScrollToIndex(7)
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Person 19").assertIsDisplayed()
    }

    @Test
    fun testSearchFunctionality() {
        val personList = mutableStateOf(emptyList<Person>())
        val isSearching = mutableStateOf(false)

        val viewModel = object : PersonListViewModel(MockPersonListInteractor()) {
            init {
                this.personList = personList
                this.isSearching = isSearching
            }
        }

        composeTestRule.setContent {
            RAMTheme {
                PersonListScreen(
                    navController = rememberNavController(),
                    viewModel = viewModel
                )
            }
        }

        personList.value = listOf(
            Person(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                image = "",
                location = PersonLocation("Earth"),
                episode = listOf()
            ),
            Person(
                id = 2,
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                image = "",
                location = PersonLocation("Earth"),
                episode = listOf()
            ),
            Person(
                id = 3,
                name = "Summer Smith",
                status = "Alive",
                species = "Human",
                image = "",
                location = PersonLocation("Earth"),
                episode = listOf()
            )
        )

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("SearchBar").performTextInput("Summer")
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Summer Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick Sanchez").assertDoesNotExist()
        composeTestRule.onNodeWithText("Morty Smith").assertDoesNotExist()
    }
}

class MockPersonListInteractor : PersonListInteractor {
    override suspend fun getPersonList(page: Int): Request<PersonListResponse> {
        return Request.Success(
            PersonListResponse(
                info = PersonListInfo(pages = 1),
                results = listOf(
                    Person(
                        id = 1,
                        name = "Rick Sanchez",
                        status = "Alive",
                        species = "Human",
                        image = "",
                        location = PersonLocation("Earth"),
                        episode = listOf()
                    )
                )
            )
        )
    }
}
