package com.rmxdev.ventapp.presenter.reports

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun SalesReportScreen(
    modifier: Modifier = Modifier,
    viewModel: SalesReportViewModel = koinViewModel()
) {

    val invoices by viewModel.invoices.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(invoices) { invoice ->
                SalesReportItem(invoice)
                Divider()
            }
        }
    }
}