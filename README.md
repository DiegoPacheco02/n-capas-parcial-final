# Parcial Final Programación N-Capas – (Seguridad con Spring Security + JWT)

Este repositorio contiene un proyecto para evaluar y practicar los conceptos de seguridad en aplicaciones Spring Boot usando JWT, roles y Docker.

### Estudiantes
- **Nombre del estudiante 1**: Diego Josué Rosa Pacheco - 00007122
- **Nombre del estudiante 2**: Mauricio Alejandro Contreras Montoya - 00000422
- Sección: 01
---

## Sistema de Soporte Técnico

### Descripción
Simula un sistema donde los usuarios pueden crear solicitudes de soporte (tickets) y los técnicos pueden gestionarlas. Actualmente **no tiene seguridad implementada**.

Su tarea es **agregar autenticación y autorización** utilizando **Spring Security + JWT**, y contenerizar la aplicación con Docker.

### Requisitos generales

- Proyecto funcional al ser clonado y ejecutado con Docker.
- Uso de PostgreSQL (ya incluido en docker-compose).
- Seguridad implementada con JWT.
- Roles `USER` y `TECH`.
- Acceso restringido según el rol del usuario.
- Evidencia de funcionamiento (colección de Postman/Insomnia/Bruno o capturas de pantalla).

**Nota: El proyecto ya tiene una estructura básica de Spring Boot con endpoints funcionales para manejar tickets. No es necesario modificar la lógica de negocio, solo agregar seguridad. Ademas se inclye un postman collection para probar los endpoints. **

_Si van a crear mas endpoints como el login o registrarse recuerden actualizar postman/insomnia/bruno collection_

### Partes de desarrollo

#### Parte 1: Implementar login con JWT
- [ ] Crear endpoint `/auth/login`.
- [ ] Validar usuario y contraseña (puede estar en memoria o en BD).
- [ ] Retornar JWT firmado.

#### Parte 2: Configurar filtros y validación del token
- [ ] Crear filtro para validar el token en cada solicitud.
- [ ] Extraer usuario desde el JWT.
- [ ] Añadir a contexto de seguridad de Spring.

#### Parte 3: Proteger endpoints con Spring Security
- [ ] Permitir solo el acceso al login sin token.
- [ ] Proteger todos los demás endpoints.
- [ ] Manejar errores de autorización adecuadamente.

#### Parte 4: Aplicar roles a los endpoints

| Rol   | Acceso permitido                                 |
|--------|--------------------------------------------------|
| USER  | Crear tickets, ver solo sus tickets              |
| TECH  | Ver todos los tickets, actualizar estado         |

- [ ] Usar `@PreAuthorize` o reglas en el `SecurityFilterChain`.
- [ ] Validar que un USER solo vea sus tickets.
- [ ] Validar que solo un TECH pueda modificar tickets.

#### Parte 5: Agregar Docker
- [ ] `Dockerfile` funcional para la aplicación.
- [ ] `docker-compose.yml` que levante la app y la base de datos.
- [ ] Documentar cómo levantar el entorno (`docker compose up`).

#### Parte 6: Evidencia de pruebas
- [ ] Probar todos los flujos con Postman/Insomnia/Bruno.
- [ ] Mostrar que los roles se comportan correctamente.
- [ ] Incluir usuarios de prueba (`user`, `tech`) y contraseñas.

### Indicaciones para pruebas
Se modificó la colección de postman original para agregar las peticiones relacionadas a la autentificación.

Para hacer uso de los endpoints, es necesario crear un usuario desde la base de datos, para posteriormente ingresar los demás usuarios con los respectivos endpoints. El script del insert es el siguiente
```sql
INSERT INTO usuarios (correo, nombre, nombre_rol, password) 
VALUES ('admin@uca.edu.sv','Administrador', 'USER','$2a$14$ZhDx1UNei/eR4a/XbrvYLu8cxArIec9jF.IeMk4ucqpjy24uimnp6');
```
**Donde la contraseña es:** `admin123`

### Capturas de pantalla
Inicio de sesión con usuario tipo USER
![image](https://github.com/user-attachments/assets/35a97da1-5083-4f26-a04f-d6748c4c8c41)

Inicio de sesión fallido por credenciales incorrectas
![image](https://github.com/user-attachments/assets/33223e30-bad7-475e-835b-fcbc1a4b29cc)

Registrando un nuevo ticket con el token obtenido anteriormente
![image](https://github.com/user-attachments/assets/00ab8ca7-69ec-4d9e-819a-d96bd4589797)

Tratando de obtener todos los tickets con el JWT de tipo USER
![image](https://github.com/user-attachments/assets/3acff316-c154-4a06-ae97-77f8a6cb321c)

Iniciando sesión con un usuario del tipo TECH
![image](https://github.com/user-attachments/assets/1db14156-0770-4a3e-a7a5-b33f580ebe57)

Obteniendo el listado de tickets con el JWT del tipo TECH creado anteriormente
![image](https://github.com/user-attachments/assets/206ce964-daa8-4b54-bfb3-069bb67f22e2)
