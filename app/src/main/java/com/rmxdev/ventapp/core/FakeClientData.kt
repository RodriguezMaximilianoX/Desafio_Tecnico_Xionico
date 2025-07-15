package com.rmxdev.ventapp.core

import com.rmxdev.ventapp.domain.entities.Client

object FakeClientData {
    fun generateClients(): List<Client> {
        val cordobaClients = listOf(
            Client(1,"Kiosco Central", "Av. Colón 850", "Kiosco", -31.4135, -64.1810),
            Client(2,"Despensa Sur", "Bv. San Juan 1200", "Despensa", -31.4179, -64.1892),
            Client(3,"Almacén Norte", "Av. Monseñor Pablo Cabrera 3500", "Almacén", -31.3596, -64.1997),
            Client(4,"MiniMarket Cerro", "Av. Rafael Núñez 4200", "MiniMarket", -31.3761, -64.2282),
            Client(5,"Tienda Centro", "9 de Julio 200", "Tienda", -31.4170, -64.1833),
            Client(6,"MaxiKiosco Nueva Cba", "Av. Vélez Sarsfield 800", "Kiosco", -31.4290, -64.1880),
            Client(7,"Super Sur", "Av. Valparaíso 3000", "Supermercado", -31.4652, -64.1917),
            Client(8,"Almacén Este", "Av. Sabattini 2900", "Almacén", -31.4397, -64.1534),
            Client(9,"Despensa Oeste", "Av. Fuerza Aérea 2300", "Despensa", -31.4245, -64.2247),
            Client(10,"MiniMarket Güemes", "Belgrano 900", "MiniMarket", -31.4270, -64.1920)
        )

        val randomClients = (11..110).map {
            Client(
                name = "Cliente $it",
                address = "Calle $it",
                category = listOf("Kiosco", "Almacén", "Despensa").random(),
                latitude = -31.45 + (it % 20) * 0.001,  // valores válidos
                longitude = -64.20 + (it % 20) * 0.001,
                hasSale = false
            )
        }

        return cordobaClients + randomClients
    }
}