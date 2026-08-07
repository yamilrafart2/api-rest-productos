# Sistema de Inventario de Productos

Aplicación web y API REST desarrollada con **Java y Spring Boot** para la gestión de productos.  
Permite realizar operaciones CRUD (Crear, Consultar, Actualizar y Eliminar) sobre el recurso **Producto** mediante una interfaz gráfica y endpoints REST, con seguridad implementada y exportación de reportes.

La aplicación corre localmente en:

```text
http://localhost:8080
```

---

## 🚀 Características Principales

- **Interfaz de Usuario:** Vistas web dinámicas renderizadas del lado del servidor.
- **Seguridad:** Autenticación de usuarios y control de accesos basados en roles (ADMIN, USER).
- **Exportación:** Generación y descarga del listado de productos en formato Excel (`.xlsx`).
- **API REST:** Endpoints estructurados para el consumo de datos desde clientes externos.

---

## 🛠️ Tecnologías utilizadas

- **Backend:** Java 21, Spring Boot, Spring Web
- **Frontend:** Thymeleaf, HTML5, CSS3
- **Persistencia:** Spring Data JPA / Hibernate, MySQL
- **Seguridad:** Spring Security, Thymeleaf Extras Spring Security
- **Utilidades:** Apache POI (Exportación a Excel), Lombok, Maven
- **Herramientas:** Postman (para pruebas de endpoints REST)

---

## 📋 Requisitos previos

Antes de ejecutar el proyecto, asegurarse de tener instalado:

- Java JDK 21 o superior
- Maven
- MySQL
- IDE (IntelliJ IDEA recomendado)

---

## ⚙️ Configuración del proyecto

Crear una base de datos en MySQL:

```sql
CREATE DATABASE productos;
```

Configurar las credenciales de conexión en:

```text
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/productos
spring.datasource.username=usuario
spring.datasource.password=password
```

---

## ▶️ Ejecutar la aplicación

Clonar el repositorio:

```bash
git clone https://github.com/yamilrafart2/api-rest-productos.git
```

Ingresar al proyecto:

```bash
cd apirest-productos
```

Ejecutar con Maven:

```bash
./mvnw spring-boot:run
```

o desde IntelliJ ejecutando la clase principal de Spring Boot (`ApirestProductosApplication`).

---

## 🌐 Rutas de la Interfaz Web (UI)

| Operación | Método | Ruta | Permisos |
|---|---|---|---|
| Iniciar Sesión | GET/POST | `/login` | Público |
| Listar Productos | GET | `/web/productos` | Autenticado (USER/ADMIN) |
| Nuevo Producto | GET/POST | `/web/productos/nuevo` | ADMIN |
| Editar Producto | GET/POST | `/web/productos/editar/{id}` | ADMIN |
| Eliminar Producto| POST | `/web/productos/eliminar/{id}`| ADMIN |
| Exportar a Excel | GET | `/web/productos/exportar/excel`| Autenticado (USER/ADMIN) |

---

## 📌 Endpoints API REST

### Crear producto
**POST** `/productos` *(Requiere rol ADMIN)*

Ejemplo de JSON:

```json
{
    "nombre": "Notebook Lenovo",
    "precio": 850000,
    "descripcion": "Notebook con procesador Intel i5"
}
```

---

### Obtener todos los productos
**GET** `/productos` *(Requiere Autenticación)*

Retorna la lista completa de productos.

---

### Obtener producto por ID
**GET** `/productos/{id}` *(Requiere Autenticación)*

Ejemplo: `GET /productos/1`

Retorna un producto específico según su identificador.

---

### Actualizar producto
**PUT** `/productos/{id}` *(Requiere rol ADMIN)*

Actualiza los datos de un producto existente.

Ejemplo:

```json
{
    "nombre": "Notebook Lenovo actualizada",
    "precio": 900000,
    "descripcion": "Nueva descripción"
}
```

---

### Eliminar producto
**DELETE** `/productos/{id}` *(Requiere rol ADMIN)*

Ejemplo: `DELETE /productos/2`

Elimina un producto por su ID.

---

# 📝 Notas

- Actualmente la aplicación está configurada para ejecutarse en un entorno local.
- La conexión a la base de datos utiliza MySQL.
- Hibernate está configurado para actualizar automáticamente las tablas:

```properties
spring.jpa.hibernate.ddl-auto=update
```

- Para ambientes productivos se recomienda utilizar variables de entorno para manejar credenciales sensibles.

---

## 👨‍💻 Autor

Yamil Rafart ♥️