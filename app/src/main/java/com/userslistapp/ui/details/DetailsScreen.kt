package com.userslistapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.userslistapp.R
import com.userslistapp.domain.models.UserDetailDomain
import com.userslistapp.domain.models.UserDomain
import com.userslistapp.domain.models.UserSupportDomain
import com.userslistapp.ui.components.ErrorScreenComponent
import com.userslistapp.ui.components.LoadingScreenComponent


@Composable
fun DetailsScreen(
    userId: Int,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        androidx.compose.ui.platform.LocalLifecycleOwner.current
    )

    var userDetail: UserDetailDomain? by remember {
        mutableStateOf(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var error by remember {
        mutableStateOf<Int?>(null)
    }

    when(uiState) {
        is DetailScreenUIState.Loading -> {
            isLoading = true
        }
        is DetailScreenUIState.Success -> {
            isLoading = false
            userDetail = (uiState as DetailScreenUIState.Success).user
        }
        is DetailScreenUIState.Error -> {
            isLoading = false
            error = (uiState as DetailScreenUIState.Error).message
        }

        is DetailScreenUIState.Init -> {
            LaunchedEffect(key1 = uiState){
                viewModel.getUserDetail(userId)
            }
        }
    }

    if (isLoading) {
        LoadingScreenComponent()
    } else if (error != null) {
        ErrorScreenComponent()
    } else if (userDetail != null) {
        DetailsScreenComponent(userDetail!!)
    }
}

@Preview
@Composable
fun DetailsScreenComponentPreview() {
    DetailsScreenComponent(
        UserDetailDomain(
            user = UserDomain(
                id = 9362,
                email = "debora.anderson@example.com",
                firstName = "Iva Strong",
                lastName = "Cynthia Collins",
                avatar = "https://reqres.in/img/faces/2-image.jpg"
            ), support = UserSupportDomain(
                url = "https://www.google.com/#q=veniam",
                text = "decore"
            )
        )
    )
}

@Composable
fun DetailsScreenComponent(userDetail: UserDetailDomain) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)
    ){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)

        ) {
            val (image, nameLabel, nameText, surnameLabel, surnameText, emailLabel,emailText) = createRefs()

            AsyncImage(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userDetail.user.avatar)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.bg_example),
                contentDescription = stringResource(R.string.content_description),
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier
                    .constrainAs(nameLabel) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 20.dp),
                text = stringResource(R.string.name_label),
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom)
                        start.linkTo(nameLabel.end)
                    }
                    .padding(start = 8.dp, top = 20.dp),
                text = userDetail.user.firstName,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(surnameLabel) {
                        top.linkTo(nameLabel.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 10.dp),
                text = stringResource(R.string.surname_label),
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(surnameText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(surnameLabel.end)
                    }
                    .padding(start = 8.dp, top = 10.dp),
                text = userDetail.user.lastName,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(emailLabel) {
                        top.linkTo(surnameLabel.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 10.dp),
                text = stringResource(R.string.email_label),
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(emailText) {
                        top.linkTo(surnameText.bottom)
                        start.linkTo(emailLabel.end)
                    }
                    .padding(start = 8.dp, top = 10.dp),
                text = userDetail.user.email,
                color = Color.Black
            )

        }
    }
}
