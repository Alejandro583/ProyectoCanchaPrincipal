# 🏟️ Sistema de Gestión de Cancha Sintética

Sistema de escritorio desarrollado en Java para la administración integral de canchas deportivas, incluyendo reservas, ventas, control de stock y gestión de clientes.

---

## 🚀 Descripción

Este proyecto consiste en el desarrollo de un sistema que permite gestionar de forma eficiente:

- Reservas de canchas
- Clientes
- Productos e inventario
- Ventas y compras
- Caja y movimientos financieros
- Reportes del sistema

El objetivo principal es reemplazar procesos manuales por un sistema automatizado, mejorando la eficiencia operativa y reduciendo errores.

---

## 🛠️ Tecnologías utilizadas

- **Lenguaje:** Java
- **Interfaz gráfica:** Java Swing
- **Base de datos:** MySQL
- **Arquitectura:** Cliente-Servidor (3 capas)
- **Patrones de diseño:**
  - MVC (Model-View-Controller)
  - DAO (Data Access Object)
  - Singleton
  - Factory

---

## ⚙️ Funcionalidades principales

### 🔐 Autenticación
- Login de usuarios
- Control de sesiones
- Validación de permisos

### 🏟️ Gestión de Canchas
- Registro y modificación de canchas
- Control de disponibilidad en tiempo real

### 📅 Reservas
- Creación y gestión de reservas
- Validación de horarios disponibles
- Prevención de conflictos

### 🛒 Ventas y Compras
- Registro de ventas y compras
- Generación automática de facturas
- Actualización de stock

### 📦 Productos
- Gestión completa de inventario
- Control automático de stock

### 👥 Clientes y Proveedores
- Registro y administración
- Búsqueda y validaciones

### 💰 Caja
- Control de ingresos y egresos
- Cálculo de saldo en tiempo real

### 📊 Reportes
- Reportes de ventas
- Reportes de reservas
- Reportes de stock

---

## 🧱 Arquitectura del sistema

El sistema está estructurado en 3 capas:

- **Vista:** Interfaces gráficas (Swing)
- **Lógica de negocio:** Controladores y reglas del sistema
- **Datos:** Acceso a base de datos (DAO)
