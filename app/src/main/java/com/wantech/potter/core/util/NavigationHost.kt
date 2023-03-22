package com.wantech.potter.core.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.potter.characters.presentation.details.DetailsScreen
import com.wantech.potter.characters.presentation.home.HomeScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.Details.route+"/{id}"){navBackStack->
            val id = navBackStack.arguments?.getString("id")
            if (id != null) {
                DetailsScreen(navController = navController,itemId=id)
            }
        }
    }
}