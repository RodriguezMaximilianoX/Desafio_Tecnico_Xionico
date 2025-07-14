package com.rmxdev.ventapp.presenter.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.ventapp.domain.entities.User
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    navigateToHome: (User) -> Unit
) {

    val loginState by viewModel.loginState.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Iniciar sesión", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(2f))
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Ingrese su email o nombre de usuario") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Ingrese su contraseña") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            Button(
                onClick = {
                    viewModel.login(email, password)
                },
                enabled = email.isNotBlank() && password.isNotBlank(),
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Iniciar sesión",fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        when (loginState) {
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }
            is LoginState.Success -> {
                val user = (loginState as LoginState.Success).user
                navigateToHome(user)
            }
            is LoginState.Error -> {
                LaunchedEffect(loginState) {
                    Toast.makeText(
                        context,
                        (loginState as LoginState.Error).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else -> {}
        }
    }
}