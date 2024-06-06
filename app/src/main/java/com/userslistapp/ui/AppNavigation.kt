package com.userslistapp.ui

sealed class InitNavigationItems(val route: String) {
    data object Main: InitNavigationItems("MainScreen")
    data object Details: InitNavigationItems("DetailsScreen?userId={userId}"){
        fun createRoute(userId: Int) = "DetailsScreen?userId=$userId"
    }
}