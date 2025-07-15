package com.rmxdev.ventapp.presenter.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rmxdev.ventapp.core.shareFile
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareScreen(
    modifier: Modifier = Modifier,
    viewModel: ShareViewModel = koinViewModel()
) {

    val context = LocalContext.current
    var selectedReport by rememberSaveable { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(Modifier.padding(padding)) {
            Button(onClick = { selectedReport = "clients" }) {
                Text("Exportar clientes")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { selectedReport = "invoices" }) {
                Text("Exportar ventas")
            }
        }

        selectedReport?.let { report ->
            AlertDialog(
                onDismissRequest = { selectedReport = null },
                title = { Text("Compartir por") },
                confirmButton = {
                    Column(Modifier.padding(16.dp)) {
                        Button(onClick = {
                            when (report) {
                                "clients" -> viewModel.exportClients {
                                    context.shareFile(it, "application/json")
                                }

                                "invoices" -> viewModel.exportInvoices {
                                    context.shareFile(it, "application/json")
                                }
                            }
                            selectedReport = null
                        }) {
                            Text("Email")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            when (report) {
                                "clients" -> viewModel.exportClients {
                                    context.shareFile(it, "application/json", "com.whatsapp")
                                }

                                "invoices" -> viewModel.exportInvoices {
                                    context.shareFile(it, "application/json", "com.whatsapp")
                                }
                            }
                            selectedReport = null
                        }) {
                            Text("WhatsApp")
                        }
                    }
                }
            )
        }

    }
}