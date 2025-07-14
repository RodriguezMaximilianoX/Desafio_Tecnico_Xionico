package com.rmxdev.ventapp.presenter.register

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
import com.rmxdev.ventapp.presenter.login.LoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = koinViewModel(),
    navigateToHome: () -> Unit
) {

    val registerState by viewModel.registerState.collectAsState()
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize()
    ){padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Registrar usuario", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(2f))
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Ingrese su nombre completo") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Ingrese su email ") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Ingrese su contraseña") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Repita su contraseña") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            Button(
                onClick = {
                    viewModel.register(email, name, password, confirmPassword)
                },
                enabled = name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank(),
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Registrar usuario",fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        when (registerState) {
            is RegisterState.Loading -> {
                CircularProgressIndicator()
            }
            is RegisterState.Success -> {
                navigateToHome()
            }
            is RegisterState.Error -> {
                LaunchedEffect(registerState) {
                    Toast.makeText(
                        context,
                        (registerState as RegisterState.Error).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else -> {}
        }
    }
}