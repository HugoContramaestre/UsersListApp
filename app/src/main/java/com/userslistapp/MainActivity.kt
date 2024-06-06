package com.userslistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.userslistapp.ui.graphs.Graph
import com.userslistapp.ui.graphs.InitNavigationGraph
import com.userslistapp.ui.theme.UsersListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalComposeApi::class,
        ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    InitNavigationGraph(
                        navHostController = rememberNavController(),
                        startDestination = Graph.INIT.route,
                        systemUiController = rememberSystemUiController()
                    )
                }
            }
        }
    }
}