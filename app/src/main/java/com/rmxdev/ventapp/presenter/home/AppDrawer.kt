package com.rmxdev.ventapp.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    userName: String,
    onItemClick: (String) -> Unit
) {
    var reportesExpanded by remember { mutableStateOf(false) }
    var mapasExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(horizontal = 16.dp, vertical = 64.dp)
    ) {
        Text("Menú")
        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem("Inicio") { onItemClick("home") }
        DrawerLabel(userName)

        DrawerItem("Clientes") { onItemClick("clients") }

        // Reportes con submenú
        DrawerExpandable(
            title = "Reportes",
            expanded = reportesExpanded,
            onExpandToggle = { reportesExpanded = !reportesExpanded }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            ) {
                DrawerItem("Reporte de Ventas") { onItemClick("reports") }
            }
        }

        // Mapas con submenú
        DrawerExpandable(
            title = "Mapas",
            expanded = mapasExpanded,
            onExpandToggle = { mapasExpanded = !mapasExpanded }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding( horizontal = 24.dp),
            ) {
                DrawerItem("Mapa de Ventas") { onItemClick("map") }
            }
        }

        DrawerItem("Enviar Datos") { onItemClick("share") }
        DrawerItem("Cerrar Sesión") { onItemClick("logout") }
    }
}

@Composable
fun DrawerItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    )
}

@Composable
fun DrawerLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
    )
}

@Composable
fun DrawerExpandable(
    title: String,
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandToggle() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        if (expanded) {
            content()
        }
    }
}