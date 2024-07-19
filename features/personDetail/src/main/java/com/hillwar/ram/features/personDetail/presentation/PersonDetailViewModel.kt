package com.hillwar.ram.features.personDetail.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hillwar.ram.core.extensions.models.Person
import com.hillwar.ram.core.network.Request
import com.hillwar.ram.features.personDetail.domain.PersonDetailInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val interactor: PersonDetailInteractor
) : ViewModel() {

    var uiState =  mutableStateOf<PersonDetailUiState>(PersonDetailUiState.Loading)

    fun getPersonDetail(personId: Int) {
        uiState.value = PersonDetailUiState.Loading

        viewModelScope.launch {
            uiState.value = when (val result = interactor.getPersonDetail(personId)) {
                is Request.Success -> PersonDetailUiState.Success(result.data)
                is Request.Error -> PersonDetailUiState.Error(result.message ?: GENERIC_ERROR_MESSAGE)
                else -> PersonDetailUiState.Error(GENERIC_ERROR_MESSAGE)
            }
        }
    }

    companion object {
        const val GENERIC_ERROR_MESSAGE = "Something went wrong."
    }
}

sealed class PersonDetailUiState {
    object Loading : PersonDetailUiState()
    data class Success(val person: Person) : PersonDetailUiState()
    data class Error(val message: String) : PersonDetailUiState()
}
