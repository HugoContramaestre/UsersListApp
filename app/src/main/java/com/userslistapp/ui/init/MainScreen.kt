package com.userslistapp.ui.init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.userslistapp.domain.models.UserDomain
import com.userslistapp.domain.models.UserListDomain
import com.userslistapp.ui.components.ErrorScreenComponent
import com.userslistapp.ui.components.ListItemComponent
import com.userslistapp.ui.components.LoadingScreenComponent


@Composable
fun MainScreen(goToDetails: (Int) -> Unit) {

    val viewModel: InitScreenViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        androidx.compose.ui.platform.LocalLifecycleOwner.current
    )

    var userList: UserListDomain? by remember {
        mutableStateOf(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var error by remember {
        mutableStateOf<Int?>(null)
    }

    when(uiState) {
        is InitScreenUIState.Loading -> {
            isLoading = true
        }
        is InitScreenUIState.Success -> {
            userList = (uiState as InitScreenUIState.Success).userList
        }
        is InitScreenUIState.Error -> {
            error = (uiState as InitScreenUIState.Error).message
        }
        is InitScreenUIState.Init -> {
            LaunchedEffect(key1 = uiState){
                viewModel.getUserList()
            }
        }
    }

    if (isLoading) {
        LoadingScreenComponent()
    } else if (error != null) {
        ErrorScreenComponent()
    }else if (userList != null) {
        MainScreenComponent(userList!!.data,goToDetails)
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreenComponent(userList = listOf(
        UserDomain(
            id = 1,
            email = "aschj@example.com",
            firstName = "Jaime",
            lastName = "Banus",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        ),
        UserDomain(
            id = 2,
            email = "aschj@example.com",
            firstName = "Jaime",
            lastName = "Banus",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        ),
        UserDomain(
            id = 3,
            email = "aschj@example.com",
            firstName = "Jaime",
            lastName = "Banus",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        )
    )){}
}

@Composable
fun MainScreenComponent(
    userList: List<UserDomain>,
    goToDetails: (Int) -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
    ) {
        val (list) = createRefs()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .constrainAs(list) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
                .fillMaxSize()
        ) {

            items(userList, key = { it.id }) {
                ListItemComponent(user = it, goToDetail = goToDetails)
            }
        }
    }
}