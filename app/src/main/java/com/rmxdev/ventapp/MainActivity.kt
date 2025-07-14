package com.rmxdev.ventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rmxdev.ventapp.core.navigation.NavigationWrapper
import com.rmxdev.ventapp.ui.theme.VentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VentAppTheme {
                NavigationWrapper()
            }
        }
    }
}
