package com.rmxdev.ventapp.core.navigation.graph

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.entities.User
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.presenter.clients.ClientScreen
import com.rmxdev.ventapp.presenter.home.HomeScreen
import com.rmxdev.ventapp.presenter.initial.InitialScreen
import com.rmxdev.ventapp.presenter.login.LoginScreen
import com.rmxdev.ventapp.presenter.register.RegisterScreen
import com.rmxdev.ventapp.presenter.reports.SalesReportScreen
import com.rmxdev.ventapp.presenter.sales.SalesScreen
import org.koin.compose.koinInject

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
    composable("clients") {
        ClientScreen(
            onClientSelected = { client ->
                navController.navigate("sales/${client.id}")
            }
        )
    }
    composable("sales/{clientId}") { backStackEntry ->

        val clientId = backStackEntry.arguments?.getString("clientId")?.toIntOrNull()
        val clientRepository: ClientRepository = koinInject()

        if (clientId != null) {
            val client by produceState<Client?>(initialValue = null, clientId) {
                value = clientRepository.getClientById(clientId)
            }

            client?.let {
                SalesScreen(
                    client = it,
                    sellerName = "Vendedor X",
                    onSaleConfirmed = { navController.navigate("clients") }
                )
            }
        }
    }
    composable("reports") {
        SalesReportScreen()
    }
}
