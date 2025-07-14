package com.rmxdev.ventapp.core.navigation.graph

import android.database.Cursor
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rmxdev.ventapp.domain.entities.User
import com.rmxdev.ventapp.presenter.home.HomeScreen
import com.rmxdev.ventapp.presenter.initial.InitialScreen
import com.rmxdev.ventapp.presenter.login.LoginScreen
import com.rmxdev.ventapp.presenter.register.RegisterScreen

fun NavGraphBuilder.mainGraph(navController: NavController, currentUser: MutableState<User?>) {

    composable("initial") {
        InitialScreen(
            navigateToLogin = {
                navController.navigate("login")
            },
            navigateToRegister = {
                navController.navigate("register")
            }
        )
    }
    composable("login") {
        LoginScreen(
            navigateToHome = { user ->
                currentUser.value = user
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }
    composable("register") {
        RegisterScreen(
            navigateToHome = { user ->
                currentUser.value = user
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }
    composable("home") {
        HomeScreen(
            userName = currentUser.value?.name ?: "Usuario",
            navigateTo = { route -> navController.navigate(route) },
            onLogOut = {
                currentUser.value = null
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            }
        )
    }
}
