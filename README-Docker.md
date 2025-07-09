# Docker Setup para API Spring Boot

Este proyecto incluye configuración Docker para ejecutar la aplicación Spring Boot con PostgreSQL.

## Requisitos

- Docker
- Docker Compose

## Comandos para ejecutar

### 1. Construir y ejecutar todos los servicios

```bash
docker-compose up --build
```

### 2. Ejecutar en segundo plano

```bash
docker-compose up -d --build
```

### 3. Ver logs

```bash
# Ver logs de todos los servicios
docker-compose logs

# Ver logs de un servicio específico
docker-compose logs app
docker-compose logs postgres
```

### 4. Detener servicios

```bash
docker-compose down
```

### 5. Detener y eliminar volúmenes

```bash
docker-compose down -v
```

## Servicios

### PostgreSQL
- **Puerto**: 5432
- **Base de datos**: supportdb
- **Usuario**: postgres
- **Contraseña**: root

### Spring Boot API
- **Puerto**: 8080
- **URL**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html

## Estructura de archivos Docker

- `Dockerfile`: Configuración para construir la imagen de la aplicación
- `docker-compose.yml`: Orquestación de servicios (app + postgres)
- `.dockerignore`: Archivos excluidos del contexto de Docker

## Variables de entorno

Las variables de entorno están configuradas en `docker-compose.yml`:

- `SPRING_DATASOURCE_URL`: URL de conexión a PostgreSQL
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos
- `SPRING_JPA_HIBERNATE_DDL_AUTO`: Configuración de Hibernate
- `SPRING_JPA_SHOW_SQL`: Mostrar SQL en logs

## Troubleshooting

### Si la aplicación no se conecta a la base de datos:

1. Verificar que PostgreSQL esté ejecutándose:
   ```bash
   docker-compose ps
   ```

2. Verificar logs de PostgreSQL:
   ```bash
   docker-compose logs postgres
   ```

3. Verificar conectividad desde la aplicación:
   ```bash
   docker-compose exec app ping postgres
   ```

### Si necesitas acceder a la base de datos:

```bash
docker-compose exec postgres psql -U postgres -d supportdb
```

### Si necesitas reiniciar solo un servicio:

```bash
docker-compose restart app
docker-compose restart postgres
``` 