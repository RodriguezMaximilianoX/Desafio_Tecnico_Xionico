package com.rmxdev.ventapp.core.navigation.graph

import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rmxdev.ventapp.presenter.home.HomeScreen
import com.rmxdev.ventapp.presenter.initial.InitialScreen
import com.rmxdev.ventapp.presenter.login.LoginScreen
import com.rmxdev.ventapp.presenter.register.RegisterScreen

fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable("initial"){
        InitialScreen(
            navigateToLogin = {
                navController.navigate("login")
            },
            navigateToRegister = {
                navController.navigate("register")
            }
        )
    }
    composable("login"){
        LoginScreen(
            navigateToHome = {
                navController.navigate("home")
            }
        )
    }
    composable("register"){
        RegisterScreen(
            navigateToHome = {
                navController.navigate("home")
            }
        )
    }
    composable("home"){
        HomeScreen()
    }
}
