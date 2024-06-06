package com.userslistapp.ui.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.SystemUiController
import com.userslistapp.ui.InitNavigationItems
import com.userslistapp.ui.details.DetailsScreen
import com.userslistapp.ui.init.MainScreen
import com.userslistapp.utils.DETAILS_SCREEN

@ExperimentalFoundationApi
@ExperimentalComposeApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun InitNavigationGraph(
    navHostController: NavHostController,
    startDestination: String,
    systemUiController: SystemUiController
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){
        initGraph(navController = navHostController, systemUiController = systemUiController)
    }
}

sealed class Graph( val route: String){
    data object INIT : Graph( "init_graph")
}

fun NavGraphBuilder.initGraph(
    navController: NavController,
    systemUiController: SystemUiController
) {
    navigation(
        route = Graph.INIT.route,
        startDestination = InitNavigationItems.Main.route
    ) {
        composable(route = InitNavigationItems.Main.route) {
            MainScreen{ id ->
                navController.navigate(InitNavigationItems.Details.createRoute(id))
            }
        }

        composable(
            route = InitNavigationItems.Details.route,
            arguments = listOf(
                navArgument(DETAILS_SCREEN) {
                    defaultValue = -1
                    type = NavType.IntType
                }
            )
        ) {
            val userId = it.arguments?.getInt(DETAILS_SCREEN) ?: -1
            DetailsScreen(userId)
        }
    }
}