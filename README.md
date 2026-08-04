# API REST - Productos

API REST desarrollada con **Java y Spring Boot** para la gestión de productos.  
Permite realizar operaciones CRUD (Crear, Consultar, Actualizar y Eliminar) sobre el recurso **Producto**.

La aplicación corre localmente en:

```
http://localhost:8080
```

---

## 🚀 Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- Spring Security
- Maven
- MySQL
- Postman (para pruebas de endpoints)

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

```
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

o desde IntelliJ ejecutando la clase principal de Spring Boot.

La API estará disponible en:

```
http://localhost:8080
```

---

# 📌 Endpoints disponibles

## Crear producto

**POST**

```
/productos
```

Crea un nuevo producto.

Ejemplo de JSON:

```json
{
    "nombre": "Notebook Lenovo",
    "precio": 850000,
    "descripcion": "Notebook con procesador Intel i5"
}
```

---

## Obtener todos los productos

**GET**

```
/productos
```

Retorna la lista completa de productos.

---

## Obtener producto por ID

**GET**

```
/productos/{id}
```

Ejemplo:

```
GET /productos/1
```

Retorna un producto específico según su identificador.

---

## Actualizar producto

**PUT**

```
/productos/{id}
```

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

## Eliminar producto

**DELETE**

```
/productos/{id}
```

Ejemplo:

```
DELETE /productos/2
```

Elimina un producto por su ID.

---

# 🧪 Pruebas con Postman

El proyecto incluye una colección de Postman con los siguientes endpoints:

| Operación | Método | Endpoint |
|---|---|---|
| Crear producto | POST | `/productos` |
| Listar productos | GET | `/productos` |
| Buscar por ID | GET | `/productos/{id}` |
| Actualizar producto | PUT | `/productos/{id}` |
| Eliminar producto | DELETE | `/productos/{id}` |

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
