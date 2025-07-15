package com.rmxdev.ventapp.presenter.sales

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rmxdev.ventapp.domain.entities.Article
import com.rmxdev.ventapp.domain.entities.Client
import com.rmxdev.ventapp.presenter.login.LoginState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SalesScreen(
    modifier: Modifier = Modifier,
    client: Client,
    sellerName: String,
    onSaleConfirmed: () -> Unit,
    viewModel: SalesViewModel = koinViewModel()
) {
    var selectedArticle by remember { mutableStateOf<Article?>(null) }
    var quantity by remember { mutableStateOf("") }

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredArticles by viewModel.filteredArticles.collectAsState()
    val saleItems by viewModel.saleItems.collectAsState()
    val totalAmount by viewModel.totalAmount.collectAsState()
    val totalProducts by viewModel.totalProducts.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Text("Cliente: ${client.name}")
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,
                label = { Text("Buscar artículo") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )

            // Lista de artículos filtrados
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                items(filteredArticles) { article ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedArticle = article
                                viewModel.onSearchQueryChanged("") // limpiar búsqueda
                            }
                            .padding(8.dp)
                    ) {
                        Text(article.name, modifier = Modifier.weight(1f))
                        Text("$${"%.2f".format(article.price)}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Artículo seleccionado y cantidad
            selectedArticle?.let { article ->
                Text("Artículo seleccionado: ${article.name}", fontWeight = FontWeight.Bold)
                Text("Precio: $${"%.2f".format(article.price)}")
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Cantidad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val qty = quantity.toIntOrNull() ?: 0
                        if (qty > 0) {
                            viewModel.addSaleItem(
                                clientId = client.id,
                                productName = article.name,
                                quantity = qty,
                                price = article.price
                            )
                            quantity = ""
                            selectedArticle = null
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar a la venta")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            Text("Productos cargados:")

            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(saleItems) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "${item.quantity} x ${item.productName} ($${item.unitPrice})",
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { viewModel.removeSaleItem(index) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Cantidad total: $totalProducts")
            Text("Importe total: $${"%.2f".format(totalAmount)}")

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.confirmSale(client, sellerName, onComplete = {
                        viewModel.clearSale()
                        onSaleConfirmed()
                    })
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = saleItems.isNotEmpty()
            ) {
                Text("Confirmar venta")
            }
            LaunchedEffect(onSaleConfirmed) {
                Toast.makeText(
                    context,
                    "Venta confirmada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}