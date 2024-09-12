package com.akshat.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akshat.readerapp.screens.ReaderSplashScreen
import com.akshat.readerapp.screens.details.BookDetailsScreen
import com.akshat.readerapp.screens.home.ReaderHomeScreen
import com.akshat.readerapp.screens.login.ReaderLoginScreen
import com.akshat.readerapp.screens.search.BookSearchViewModel
import com.akshat.readerapp.screens.search.ReaderSearchScreen
import com.akshat.readerapp.screens.stats.ReaderStatsScreen
import com.akshat.readerapp.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name){
        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name){
            ReaderStatsScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name){
            val viewModel = hiltViewModel<BookSearchViewModel>()
            ReaderSearchScreen(navController = navController,viewModel)
        }

        composable(ReaderScreens.UpdateScreen.name){

            BookUpdateScreen(navController = navController)
        }

        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        }) ){backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                BookDetailsScreen(navController = navController, bookId = it.toString())
            }

        }


    }

}