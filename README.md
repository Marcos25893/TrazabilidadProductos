# Sistema de Trazabilidad de Productos - API REST

## 1. Descripción del proyecto

Esta aplicación es un sistema de trazabilidad que permite gestionar productos. Implementa un modelo de datos con **Productos**, **Lotes** y **Eventos de trazabilidad**.

La seguridad está gestionada con **Spring Security y JWT**, garantizando que solo usuarios registrados puedan gestionar dichos productos.

---

## 2. Instrucciones de ejecución y despliegue

### Ejecución en AWS

1. **Base de Datos**

   Arrancar la base de datos si no lo está:

   ```bash
   podman start api_productos
   ```

2. **Spring**

   Irse a la carpeta `TrazabilidadProductos` y arrancar la aplicación de Spring mediante el comando:

   ```bash
   java -jar build/libs/trazabilidad_productos-0.0.1-SNAPSHOT.jar
   ```

---

## 3. Endpoints principales

### 1. Públicos (Auth)

- **POST** `http://34.232.220.111:8080/auth/register`
  Registro de nuevos usuarios.

- **POST** `http://34.232.220.111:8080/auth/login`
  Login y obtención del token Bearer JWT.

---

### 2. Privados (Requieren cabecera Authorization)

#### Productos

- **GET** `http://34.232.220.111:8080/api/productos`
  Listado resumido de productos.

- **POST** `http://34.232.220.111:8080/api/productos`
  Registro de nuevo producto.

- **PUT** `http://34.232.220.111:8080/api/productos/{id}`
  Actualización de datos de producto.

#### Lotes

- **POST** `http://34.232.220.111:8080/api/productos/{id}/lotes`
  Crear lote asociado a un producto.

- **GET** `http://34.232.220.111:8080/api/productos/{id}/lotes`
  Listar lotes por producto.

- **PUT** `http://34.232.220.111:8080/api/lotes/{id}/estado`
  Actualizar el estado actual de un lote.

#### Eventos

- **POST** `http://34.232.220.111:8080/api/lotes/{id}/eventos`
  Registrar un nuevo evento de trazabilidad.

- **GET** `http://34.232.220.111:8080/api/lotes/{id}/eventos`
  Historial completo de un lote (ordenado por fecha).

- **GET** `http://34.232.220.111:8080/api/lotes/{id}/eventos/tipo/{tipo}`
  Filtrado por tipo de evento.

- **GET** `http://34.232.220.111:8080/api/lotes/{id}/eventos/fechas?inicio=...&fin=...`
  Filtrado por rango de fechas.