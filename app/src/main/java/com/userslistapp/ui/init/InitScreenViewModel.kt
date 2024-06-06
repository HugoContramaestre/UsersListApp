package com.userslistapp.ui.init

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.userslistapp.R
import com.userslistapp.domain.DataState
import com.userslistapp.domain.models.UserListDomain
import com.userslistapp.domain.usecases.GetUserListUseCase
import com.userslistapp.ui.details.DetailScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface InitScreenUIState {
    data object Loading: InitScreenUIState
    data class Success(val userList: UserListDomain): InitScreenUIState
    data class Error(val message: Int): InitScreenUIState
    data object Init: InitScreenUIState
}

data class InitScreenViewModelState(
    val isLoading: Boolean = false,
    val userList: UserListDomain? = null,
    val error: Int? = null
) {
    fun toUIState(): InitScreenUIState =
        if (userList == null && isLoading) {
            InitScreenUIState.Init
        } else if (error != null) {
            InitScreenUIState.Error(error)
        } else if (userList != null) {
            InitScreenUIState.Success(userList)
        } else {
            InitScreenUIState.Loading
        }
}

@HiltViewModel
class InitScreenViewModel @Inject constructor(
    val handle: SavedStateHandle,
    private val getUserListUseCase: GetUserListUseCase
):  ViewModel(){

    private val viewModelState = MutableStateFlow(InitScreenViewModelState(isLoading = true))

    val uiState = viewModelState
        .map(InitScreenViewModelState::toUIState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailScreenUIState.Loading
        )

    fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {

            when(val response = getUserListUseCase()){
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
                            userList = response.data
                        )
                    }
                }
            }
        }
    }
}