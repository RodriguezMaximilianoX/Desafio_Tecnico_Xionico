package com.rmxdev.ventapp.presenter.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rmxdev.ventapp.domain.entities.Invoice
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SalesReportItem(
    invoice: Invoice
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Fecha: ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(invoice.timestamp))}",
            color = Color.Gray
        )
        Text("Cliente: ${invoice.clientName}")
        Text("Monto de la venta: $${"%.2f".format(invoice.totalAmount)}")
        Text("Cantidad de artículos: ${invoice.totalProducts}")
    }
}