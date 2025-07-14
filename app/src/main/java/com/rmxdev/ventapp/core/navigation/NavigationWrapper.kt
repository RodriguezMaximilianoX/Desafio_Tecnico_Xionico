package com.rmxdev.ventapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rmxdev.ventapp.core.navigation.graph.mainGraph

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "initial"
    ) {
        mainGraph(navController)
    }

}