package com.rmxdev.ventapp.presenter.clients

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rmxdev.ventapp.domain.entities.Client

@Composable
fun ClientItem(
    modifier: Modifier = Modifier,
    client: Client,
    onClick: (Client) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(client) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (client.hasSale) Icons.Default.CheckCircle else Icons.Default.Person,
            contentDescription = null,
            tint = if (client.hasSale) Color.Green else Color.Gray,
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(client.name)
            Text(client.address, color = Color.Gray)
        }
    }
}