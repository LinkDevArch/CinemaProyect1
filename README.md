# CinemaProyect1

**Simulador de administración de salas de cine** desarrollado en JavaFX.

## 📖 Descripción

Este proyecto es una aplicación de escritorio que permite:

- **Gestión de películas**: agregar, editar y eliminar películas con atributos como nombre, idioma, tipo y duración. [AgregarController.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/controller/AgregarController.java)
- **Asignación de funciones**: programar películas en salas y franjas horarias, con validaciones (Sala 3 solo 3D, no duplicados). [AsignarController.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/controller/AsignarController.java)
- **Venta de entradas**: selección interactiva de asientos en grillas por sala, cálculo dinámico de precios (general, preferencial y 3D) y emisión de factura. [VentasController.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/controller/VentasController.java), [CalcularPrecios.java](https://github.com/LinkDevArch/CinemaProyect1/blob/master/src/main/java/model/CalcularPrecios.java)

## 📸 Capturas
### Menu del programa botones (Agregar, Asignar, Comprar, Salir) respectivamente
![image](https://github.com/user-attachments/assets/5dd5f9e8-e2a4-4f2e-919c-494d61c0cce3)

### Menu de agregación de peliculas
![image](https://github.com/user-attachments/assets/614b0357-1e9c-4f82-8621-1c473194b197)

### Menu de Asignación de funciones
![image](https://github.com/user-attachments/assets/03aa7da5-8ff5-40f9-a816-a82f1819c3ce)

### Menu de compra
![image](https://github.com/user-attachments/assets/f0c6943d-f596-4d92-9118-089fe7bfc612)

## 🛠️ Tecnologías utilizadas

- **Java 22**
- **JavaFX 23** (FXML + CSS)
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
```

## ⚙️ Requisitos de ejecución
Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- Java Development Kit (JDK) 11 o superior
- Apache Maven
- Un IDE compatible con Java (Preferiblemente IntelliJ)

Puedes verificar si tienes Java y Maven instalados ejecutando:
```plaintext
java -version
mvn -v
```

## 🚀 Instalación y ejecución
Sigue estos pasos para correr el proyecto localmente:
1. Clona el repositorio:
   ```plaintext
   git clone https://github.com/LinkDevArch/CinemaProyect1.git
   ```
2. Accede al directorio del proyecto:
   ```plaintext
   cd CinemaProyect1
   ```
3. Ejecuta la aplicación usando Maven:
   ```plaintext
   mvn clean javafx:run
   ```
   
## 🎮 Uso
- Agregar: Oprime el primer boton para agregar peliculas.
- Asignar: Asocia películas a salas en horarios disponibles.
- Ventas: Elige una función, selecciona asientos y finaliza la compra con una factura.
- El sistema aplica restricciones como evitar funciones duplicadas o que una sala incompatible tenga una película no 3D.

## 📄 Licencia
```plaintext
Free
```
