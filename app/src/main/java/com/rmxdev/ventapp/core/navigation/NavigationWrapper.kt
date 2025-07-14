package com.rmxdev.ventapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rmxdev.ventapp.core.navigation.graph.mainGraph
import com.rmxdev.ventapp.domain.entities.User

@Composable
fun NavigationWrapper(
    navController: NavHostController = rememberNavController()
) {

    val currentUser = remember { mutableStateOf<User?>(null) }

    NavHost(
        navController = navController,
        startDestination = "initial"
    ) {
        mainGraph(navController, currentUser)
    }
}