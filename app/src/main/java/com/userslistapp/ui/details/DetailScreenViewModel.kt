package com.userslistapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.userslistapp.R
import com.userslistapp.domain.DataState
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.usecases.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface DetailScreenUIState {
    data object Loading: DetailScreenUIState
    data class Success(val user: UserDetailDomain): DetailScreenUIState
    data class Error(val message: Int): DetailScreenUIState
    data object Init: DetailScreenUIState
}

data class DetailScreenViewModelState(
    val isLoading: Boolean = false,
    val user: UserDetailDomain? = null,
    val error: Int? = null
) {
    fun toUIState(): DetailScreenUIState =
        if (user == null && isLoading) {
            DetailScreenUIState.Init
        } else if (error != null) {
            DetailScreenUIState.Error(error)
        } else if (user != null) {
            DetailScreenUIState.Success(user)
        } else {
            DetailScreenUIState.Loading
        }
}

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    val handle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase
): ViewModel() {

    private val viewModelState = MutableStateFlow(DetailScreenViewModelState(isLoading = true))

    val uiState = viewModelState
        .map(DetailScreenViewModelState::toUIState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailScreenUIState.Loading
        )

    fun getUserDetail(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            when(val response = getUserDetailUseCase(userId)){
                is DataState.Error -> {
                    viewModelState.update {
                        // podria usar pasar el mensaje de error del server si tuviese que hacerlo
                        it.copy(
                            error = R.string.error_generic
                        )
                    }
                }
                is DataState.Success -> {
                    viewModelState.update {
                        it.copy(
                            user = response.data,
                        )
                    }
                }
            }
        }
    }
}