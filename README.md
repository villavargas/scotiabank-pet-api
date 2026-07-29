# Scotiabank Pet API

API REST construida como ejercicio tecnico para consultar y registrar mascotas mediante la API publica de Petstore.

## Tecnologias

- Java 17.
- Spring Boot 3.2.7.
- Gradle 8.7.
- Spring Web y RestTemplate.
- Spring Boot Actuator.
- springdoc OpenAPI / Swagger UI.
- JUnit 5, Mockito y Lombok.

## Arquitectura

```text
src/main/java/com/example/exam/
|- client/       Cliente REST para Petstore.
|- config/       Configuracion de HTTP y propiedades externas.
|- constant/     Constantes de la aplicacion.
|- controller/   Endpoints REST.
|- exception/    Manejo centralizado de errores.
|- filter/       Trazabilidad con X-Correlation-ID.
|- model/        Objetos de request y response.
`- service/      Puerto de entrada y logica de negocio.
```

## Requisitos

- JDK 17.
- Conexion a internet para consumir `https://petstore.swagger.io/v2`.

En una red corporativa que intercepte HTTPS, Java debe confiar en el almacen de certificados de Windows. El proyecto ya lo configura al iniciar la aplicacion.

## Ejecutar la aplicacion

Desde la raiz del proyecto:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
$env:GRADLE_OPTS = "-Djavax.net.ssl.trustStoreType=Windows-ROOT"
.\gradlew.bat bootRun
```

La aplicacion inicia en `http://localhost:8080`.

## Documentacion API

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Health check: `http://localhost:8080/actuator/health`

## Endpoints

### Crear mascota

```http
POST /api/pet
Content-Type: application/json
X-Correlation-ID: postman-scotiabank-001
```

```json
{
	"id": 55667788,
	"name": "testingPet1",
	"status": "available"
}
```

Respuesta exitosa: `201 Created`.

```json
{
	"transactionId": "9d8f5a10-eb48-4ee6-bd97-351a84c77497",
	"dateCreated": "2026-07-29T16:06:16.2608941",
	"status": true,
	"name": "testingPet1"
}
```

### Consultar mascota

```http
GET /api/pet/{petId}
X-Correlation-ID: postman-scotiabank-001
```

Respuesta exitosa: `200 OK`.

```json
{
	"id": 55667788,
	"name": "testingPet1",
	"status": "available"
}
```

## Manejo de errores

| Escenario | HTTP | Resultado |
| --- | --- | --- |
| Mascota no encontrada | `404` | Mensaje controlado: `La mascota solicitada no existe`. |
| Request invalido | `400` | Lista de campos requeridos o invalidos. |
| Error no controlado | `500` | Respuesta estandarizada sin exponer detalles internos. |

## Trazabilidad

Cada request admite el header `X-Correlation-ID`. Si no se envia, la aplicacion genera un UUIDv4 y lo devuelve en el response. El mismo valor se incluye en el contexto de los logs para correlacionar una solicitud entre capas.

## Pruebas

Ejecutar las pruebas unitarias:

```powershell
.\gradlew.bat test
```

Incluye pruebas para:

- Consulta exitosa de mascota.
- Propagacion de mascota no encontrada.
- Registro de mascota con UUIDv4, fecha y estado exitoso.
- Carga del contexto Spring.

## Coleccion Postman

Importa [Scotiabank-Exam.postman_collection.json](Scotiabank-Exam.postman_collection.json) en Postman. La coleccion v2.1 contiene pruebas automatizadas para:

1. Crear una mascota y validar `201 Created`, UUIDv4 y trazabilidad.
2. Consultar la mascota creada y validar `200 OK`.
3. Validar el error controlado `404`.
4. Validar errores de entrada `400`.
5. Validar el endpoint de salud en `/actuator/health`.

Ejecuta primero el request de creacion y despues la consulta. La API publica de Petstore puede limpiar sus datos periodicamente.
