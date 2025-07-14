package com.rmxdev.ventapp.core.navigation.graph

import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rmxdev.ventapp.presenter.initial.InitialScreen

fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable("initial"){
        InitialScreen(

        )
    }
}
