package com.rmxdev.ventapp.presenter.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.rmxdev.ventapp.core.shareJsonFile
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.domain.entities.Invoice
import com.rmxdev.ventapp.domain.repository.ClientRepository
import com.rmxdev.ventapp.domain.repository.InvoiceRepository
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ShareScreen(
    modifier: Modifier = Modifier,
    viewModel: ShareViewModel = koinViewModel(),
) {

    val context = LocalContext.current
    val clientRepository: ClientRepository = koinInject()
    val invoiceRepository: InvoiceRepository = koinInject()

    val clients by produceState(initialValue = emptyList<Client>()) {
        value = clientRepository.getAllClients().first()
    }

    val invoices by produceState(initialValue = emptyList<Invoice>()) {
        value = invoiceRepository.getAllInvoices().first()
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Compartir reportes", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(2f))
            Button(onClick = {
                val json = Gson().toJson(clients)
                context.shareJsonFile("clientes.json", json, "Listado de Clientes")
            }) {
                Text("Compartir Clientes")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val json = Gson().toJson(invoices)
                context.shareJsonFile("ventas.json", json, "Reporte de Ventas")
            }) {
                Text("Compartir Ventas")
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}