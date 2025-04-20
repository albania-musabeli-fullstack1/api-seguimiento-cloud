# FullStack 1 - Actividad Sumativa 2

## API Seguimiento de envíos

API creada en Springboot para gestionar el seguimiento de envíos.

## Dependencias

* Springboot-starter-web
* Springboot-devtools
* Springboot-starter-data-jpa
* Lombok
* Oracle Driver: ojdbc
* oracle pki
* osdt_core
* osdt_cert

## Configurar variables de entorno para conexión a Oracle Cloud

* Copiar archivo .env.example y renombrar a .env
* Completar las variables de entorno relacionadas con el nombre de la base de datos, dirección del Wallet descomprimido,
  nombre de usuario y password.

## Endpoints para ambiente de Desarrollo

* GET: Obtener todos los seguimientos: http://localhost:8081/api/seguimiento/getSeguimientos
* GET: Obtener seguimiento por ID: http://localhost:8081/api/seguimiento/{id}
* POST: Crear un nuevo registro de seguimiento: http://localhost:8081/api/seguimiento
* PUT: Actualizar un seguimiento: http://localhost:8081/api/seguimiento/{id}
* DELETE: Eliminar un seguimiento: http://localhost:8081/api/seguimiento/{id}
* POST: Ubicación Actual: http://localhost:8081/api/seguimiento/getUbicacionActual

## Ejemplo para Request Body - Seguimiento

```json
{
  "codSeguimiento":"ED45TGH7CVFD46MJ78FE",
  "remitente":"Maria Soto",
  "destinatario":"Juan Gonzalez",
  "paisOrigen":"Chile",
  "paisDestino":"Peru",
  "fechaEnvio":"2025-04-19T15:30:00",
  "estado": "EN BODEGA",
  "ubicacionActual": "CENTRO DE DISTRIBUCION IQUIQUE"
}
```

## Ejemplo para Request Body - Obtener ubicación actual del envío

```json
{
  "codSeguimiento":"ED45TGH7CVFD46MJ78FE"
}
```
