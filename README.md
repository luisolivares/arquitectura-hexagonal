# API REST Bancaria con Spring Boot 3 + Docker Compose

Este proyecto es una **API REST** construida con **Spring Boot 3**, utilizando **H2** como base de datos en memoria y
documentada con **Swagger OpenAPI**.

Se provee un `Dockerfile` y un `docker-compose.yml` para facilitar la ejecución sin necesidad de tener Maven ni JDK
instalados en la máquina host.

Este proyecto exponga un CRUD completo sobre entidades bancarias, es decir, el CRUD debe permitir Altas, bajas,
modificaciones, y consultas.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA (con H2 en memoria)
- Spring Validation
- Springdoc OpenAPI (Swagger UI)
- JUnit Mockito
- Maven
- Docker & Docker Compose

---

### Cómo levantar el proyecto

### 1. Descargamos nuestro proyecto en el repositorio.

```sh
  git clone https://github.com/luisolivares/arquitectura-hexagonal.git
```

### 2. Ejecutar el Docker Desktop.

### 3. Nos ubicamos en la raíz del proyecto.

```sh
   cd arquitectura-hexagonal
```

### 4. Levantar la aplicación con Docker Compose

```sh
   docker compose up --build
```

Esto hará:

Construir el JAR del proyecto usando Maven dentro del contenedor.

Crear la imagen de la aplicación Spring Boot.

Levantar el contenedor mapeando el puerto 8080.

- Construir el JAR del proyecto con Maven dentro de un contenedor.

- Crear las imágenes necesarias.

- Levantar los contenedores de:

    - Microservicio Spring Boot (puerto 8080)

    - Prometheus (puerto 9090)

    - Grafana (puerto 3000)

### 5. Acceder a la aplicación

- **API Base** → http://localhost:8080/challange-api-rest-banco

- **API Base para verificar funcionamiento** → http://localhost:8080/challange-api-rest-banco/api/v1/healthz

- **Swagger UI** → http://localhost:8080/challange-api-rest-banco/swagger-ui/index.html

- **Consola H2** → http://localhost:8080/challange-api-rest-banco/h2-console

- **Driver:** org.h2.Driver

- **URL:** jdbc:h2:mem:banco;MODE=MySQL

- **User:** sa

- **Password:** sa

---

### Monitoreo con Prometheus y Grafana

🔹 Spring Boot Actuator + Prometheus

La aplicación expone métricas en formato Prometheus: http://localhost:8080/challange-api-rest-banco/actuator/prometheus

Prometheus está configurado para scrapear automáticamente este endpoint gracias al archivo `prometheus.yml`.

🔹 Prometheus

Accede a la interfaz web de Prometheus:
👉 http://localhost:9090

Allí podrás consultar métricas como:

- `http_server_requests_seconds_count`

- `jvm_memory_used_bytes`

- `system_cpu_usage`

🔹 Grafana

Accede a Grafana en:
👉 http://localhost:3000

Credenciales por defecto:

- Usuario: admin

- Contraseña: admin

#### Configuración inicial en Grafana:

- Ingresar con admin / admin (se pedirá cambiar la contraseña).

- Ir a Connections → Data sources → Add data source.

- Seleccionar Prometheus.

- En la URL colocar: `http://prometheus:9090`.

- Guardar y probar conexión.

- Importar un dashboard de métricas de Spring Boot desde Grafana Marketplace (ejemplo ID: 4701).

---

## Comandos utiles

### Detener la aplicación si está corriendo en primer plano

Presioná **Ctrl + C** en la terminal donde ejecutaste `docker compose up`.

### Si está corriendo en segundo plano (detached mode)

`docker compose down`.

### Ver logs de la aplicación

`docker logs -f banco-container`

### Ejecución en segundo plano

Para correr sin logs en la terminal:

```sh
docker compose up -d --build
```

Y para detener:

```sh
docker compose down
```

---

### Notas

La base de datos H2 es en memoria, por lo que los datos se pierden al detener el contenedor.

Swagger UI ya está habilitado por defecto para documentar la API.

Con este setup, no es necesario tener Maven ni JDK en la máquina host, todo se construye dentro del contenedor.