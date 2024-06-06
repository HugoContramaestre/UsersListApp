package com.userslistapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.userslistapp.R
import com.userslistapp.domain.models.UserDomain

@Preview
@Composable
fun ListItemComponentPreview(){
    ListItemComponent(
        user = UserDomain(
            id = 1,
            email = "aschj@example.com",
            firstName = "Jaime",
            lastName = "Banus",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        )
    ){}
}

@Composable
fun ListItemComponent(user: UserDomain, goToDetail: (Int) -> Unit = {}) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = BorderStroke(1.dp, Color.Gray),
        onClick = { goToDetail.invoke(user.id) }
    ) {
        Row {
            Spacer(8.dp)

            AsyncImage(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.bg_example),
                contentDescription = stringResource(R.string.content_description),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(
                        vertical = 15.dp,
                        horizontal = 8.dp
                    )
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier,
                    text = user.firstName + " " + user.lastName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )

                Spacer(8.dp)

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.custom_color)
                                )
                            ) {
                                append(user.email)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.Spacer(length: Dp) {
    Spacer(
        modifier = Modifier.width(length)
    )
}

@Composable
private fun ColumnScope.Spacer(length: Dp) {
    Spacer(
        modifier = Modifier.height(length)
    )
}