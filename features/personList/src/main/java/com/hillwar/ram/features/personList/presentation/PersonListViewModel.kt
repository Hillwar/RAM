package com.hillwar.ram.features.personList.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.features.personList.domain.PersonListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class PersonListViewModel @Inject constructor(
    private val interactor: PersonListInteractor
) : ViewModel() {

    var personList = mutableStateOf<List<Person>>(listOf())
    var loadError = mutableStateOf(EMPTY)
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)
    var isSearching = mutableStateOf(false)

    var curPage = FIRST_PAGE
    var cachedPersonList = listOf<Person>()
    var isSearchStarting = true

    fun loadPersons() = viewModelScope.launch {
        isLoading.value = true

        when (val result = interactor.getPersonList(curPage)) {
            is Request.Success -> {
                curPage++

                endReached.value = curPage >= result.data.info.pages
                loadError.value = EMPTY
                isLoading.value = false
                personList.value += result.data.results.shuffled()
            }

            is Request.Error -> {
                loadError.value = result.message ?: EMPTY
                isLoading.value = false
            }

            else -> {
                loadError.value = GENERIC_ERROR_MESSAGE
                isLoading.value = false
            }
        }
    }


    fun searchPerson(query: String): Job {
        val listToSearch = if (isSearchStarting) {
            personList.value
        } else {
            cachedPersonList
        }

        return viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                personList.value = cachedPersonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchStarting) {
                cachedPersonList = personList.value
                isSearchStarting = false
            }

            personList.value = results
            isSearching.value = true
        }
    }

    companion object {
        const val FIRST_PAGE = 1
        const val EMPTY = ""
        const val GENERIC_ERROR_MESSAGE = "Something went wrong."
    }
}
