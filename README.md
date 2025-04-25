# CinemaProyect1

**Simulador de administración de salas de cine** desarrollado en JavaFX.

## 📖 Descripción

Este proyecto es una aplicación de escritorio que permite:

- **Gestión de películas**: agregar, editar y eliminar películas con atributos como nombre, idioma, tipo y duración. [AgregarController.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/controller/AgregarController.java)
- **Asignación de funciones**: programar películas en salas y franjas horarias, con validaciones (Sala 3 solo 3D, no duplicados). [AsignarController.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/controller/AsignarController.java)
- **Venta de entradas**: selección interactiva de asientos en grillas por sala, cálculo dinámico de precios (general, preferencial y 3D) y emisión de factura. [VentasController.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/controller/VentasController.java), [CalcularPrecios.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/model/CalcularPrecios.java)

## 📸 Capturas

> *Aquí puedes añadir capturas de pantalla de la aplicación*  
> Ejemplo:  
> `![Gameplay](screenshots/gameplay.png)`

## 🛠️ Tecnologías utilizadas

- **Java 11**
- **JavaFX 23**
- **FXML** + **CSS**
- **Maven**
- **Arquitectura MVC**:
  - `application/`: clase principal JavaFX (`App.java`) que carga la vista. [App.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/application/App.java)
  - `controller/`: lógica de interacción con UI (`MainController.java`, `AgregarController.java`, `AsignarController.java`, `VentasController.java`, `FacturaController.java`)
  - `model/`: modelos de datos y lógica de negocio (`Pelicula.java`, `HorarioSala.java`, `CineData.java`, `CalcularPrecios.java`)
  - `utilities/`: rutas de recursos (`Path.java`)

## 📂 Estructura de directorios

```plaintext
src/
├─ main/
│  ├─ java/
│  │  ├─ application/ App.java
│  │  ├─ controller/
│  │  │   ├─ MainController.java
│  │  │   ├─ AgregarController.java
│  │  │   ├─ AsignarController.java
│  │  │   ├─ VentasController.java
│  │  │   └─ FacturaController.java
│  │  ├─ model/
│  │  │   ├─ Pelicula.java
│  │  │   ├─ HorarioSala.java
│  │  │   ├─ CineData.java
│  │  │   └─ CalcularPrecios.java
│  │  └─ utilities/
│  │      └─ Path.java
│  └─ resources/
│      ├─ fxml/
│      ├─ css/
│      └─ img/
└─ test/
