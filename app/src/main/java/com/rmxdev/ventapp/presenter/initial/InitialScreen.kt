package com.rmxdev.ventapp.presenter.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Bienvenido a VentApp", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(2f))
            Button(
                onClick = navigateToLogin,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Iniciar Sesión", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Button(
                onClick = navigateToRegister,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Registrarse",fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}