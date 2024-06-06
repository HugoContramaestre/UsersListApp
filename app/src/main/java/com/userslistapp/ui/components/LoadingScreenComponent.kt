package com.userslistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.userslistapp.R

@Preview
@Composable
fun LoadingScreenPreview(){
    LoadingScreenComponent()
}


@Composable
fun LoadingScreenComponent(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val(text) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(text){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                ) {
                    append(stringResource(R.string.loading))
                }
            }
        )
    }
}