# 🧾 VentApp — App de Gestión de Ventas con Geolocalización

VentApp es una aplicación móvil desarrollada en **Android con Kotlin** y **Jetpack Compose**, diseñada para que vendedores gestionen sus clientes, realicen ventas, visualicen reportes y ubiquen clientes con ventas en un mapa. Cumple con todos los requerimientos planteados en el desafío técnico.

---

## 🚀 Funcionalidades principales

### 👤 Gestión de usuarios
- Registro e inicio de sesión local.
- Almacenamiento seguro de contraseñas con hash.
- Navegación protegida según autenticación.

### 🧍‍♂️ Clientes
- Alta y búsqueda de clientes.
- Almacenamiento de domicilio y geolocalización.
- Identificación visual de clientes con ventas realizadas.

### 🧾 Ventas
- Carga de productos ya existentes en la base.
- Selección de artículos con precio automático.
- Cálculo del importe total y cantidad de productos.
- Confirmación de venta y generación de factura.
- Relación entre Cliente ↔ Venta ↔ Vendedor.

### 📊 Reporte de Ventas
- Visualización de todas las ventas confirmadas.
- Información mostrada:
  - Fecha
  - Cliente
  - Monto total
  - Cantidad de artículos

### 🗺 Mapa de Ventas
- Mapa con marcadores de clientes que realizaron compras.
- Al presionar un marcador se muestra:
  - Cliente
  - Vendedor
  - Importe
  - Cantidad de artículos

### 📤 Exportación y Compartir
- Exportación de:
  - Lista de clientes
  - Reporte de ventas
- Compartir vía WhatsApp, Gmail, etc. en formato `.json`.

---

## 🛠️ Tecnologías y herramientas

- **Kotlin**
- **Jetpack Compose**
- **Room (Base de datos local)**
- **Koin (Inyección de dependencias)**
- **Google Maps Compose**
- **Gson (Serialización JSON)**
- **FileProvider (Compartir archivos)**

---

## 📦 Estructura del proyecto (Clean Architecture)
