# Foro - Challenge API REST

Este proyecto es una API REST desarrollada como parte de un challenge. Proporciona un sistema para gestionar un foro con funcionalidades como autenticación, recuperación de contraseñas, documentación interactiva y soporte para páginas protegidas mediante JWT y OAuth 2.0.

## Características principales

- **Autenticación y Autorización**: Implementación de autenticación mediante JWT con llaves pública y privada.
- **Recuperación de contraseñas**: Los usuarios pueden solicitar un enlace de restablecimiento de contraseña a través de correo electrónico.
- **Documentación Swagger**: Acceso a la documentación interactiva de la API mediante SpringDoc y Swagger UI.
- **Paginación y Ordenamiento**: Soporte para páginas y ordenamiento dinámico de resultados.
- **Migrations**: Uso de Flyway para la gestión de migraciones de base de datos.
- **Seguridad**: Integración de Spring Security con soporte para OAuth 2.0 y roles de usuario.

## Requisitos previos

- **Java 17** o superior
- **Maven 3.6** o superior
- **MySQL**
- Servidor SMTP configurado para el envío de correos (p. ej., Gmail)

## Configuración

1. Clonar el repositorio:

   ```bash
   git clone <URL-del-repositorio>
   cd foro
   ```

2. Configurar las variables de entorno en un archivo `.env` o como variables de entorno del sistema:

   ```properties
   PORT=8080
   URL=jdbc:mysql://<host>:<puerto>/<nombre_base_datos>
   USERNAME=<tu_usuario>
   PASSWORD=<tu_contraseña>
   SPRING_MAIL_USERNAME=<tu_correo>
   SPRING_MAIL_PASSWORD=<tu_contraseña_correo>
   SPRING_MAIL_HOST=smtp.gmail.com
   SPRING_MAIL_PORT=587
   JWT_PUBLIC_KEY=classpath:public.pem
   JWT_PRIVATE_KEY=classpath:private.pem
   ```

3. Crear las llaves pública y privada para JWT:

   ```bash
   openssl genrsa -out private.pem 2048
   openssl rsa -in private.pem -pubout -out public.pem
   ```

   Coloca estos archivos en el directorio `src/main/resources`.

4. Configurar la base de datos:

   Asegúrate de tener una base de datos MySQL configurada y crea las tablas necesarias ejecutando las migraciones de Flyway automáticamente al iniciar la aplicación.

## Instalación y Ejecución

1. Compilar y empaquetar la aplicación:

   ```bash
   mvn clean install
   ```

2. Ejecutar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

3. Acceder a la documentación Swagger en con el puerto que este configurado:

   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


   ![Image](https://github.com/user-attachments/assets/67b811e0-cd03-4f2b-a49b-596b571854f2)

## Tecnologías utilizadas

- **Spring Boot 3.4.1**
- **Spring Data JPA**: Para la gestión de datos.
- **Spring Security**: Para la seguridad de la API.
- **Flyway**: Migraciones de base de datos.
- **MySQL**: Base de datos relacional.
- **Swagger y SpringDoc**: Documentación interactiva.
- **JWT**: Autenticación basada en tokens.
- **Lombok**: Simplificación del código boilerplate.
- **Spring Boot Starter Mail**: Para envío de correos electrónicos.
- **Nimbus JOSE + JWT**: Firma y verificación de tokens JWT.

## Endpoints destacados

1. **Autenticación**:
   - `POST /login`: Autenticación de usuarios.
     
 ![Image](https://github.com/user-attachments/assets/87c6cdee-7151-4096-bcd2-2aa78cf1641c)


     
   - `POST /auth/singup`: Creación de un nuevo usuario.

  ![Image](https://github.com/user-attachments/assets/926d6a0c-b2cc-4da4-b783-4623ebea970f)
     

1. **Recuperación de contraseñas**:
   - `POST /auth/reset-password/request`: Solicitud de restablecimiento de contraseña y ennvia un mensaje al correo del usuario .
   - 
![Image](https://github.com/user-attachments/assets/562efd05-263e-46e9-9d7f-b443a9ae8c55)

   - `POST /auth/reset-password`: Solicitud de restablecimiento de contraseña.
   - 
![Image](https://github.com/user-attachments/assets/3bcce503-ae1f-4266-b188-42671229d2bc)

- Usamos la antigua contraseña pero nos dice que esta mal el password pues lo cambiamos.
- 
    ![Image](https://github.com/user-attachments/assets/9b246867-3de7-450e-b913-4d2920cfbe37)
  
- Usamos la nueva conraseña  para  el login.
  
    ![Image](https://github.com/user-attachments/assets/4ed0b03e-3f23-4f35-95ca-b0fc00309a98)
  

4. **Gestión de usuarios**:
   - `GET /user`: Listado de usuarios (paginado).
5. **Gestión de cursos del foro**:
   - `GET /course`: Listado de cursos (paginado y ordenado).
   - `POST /course`: Creación de un nuevo curso.
   - `GET /course/id`: Obtiene un  curso por su id.
   - `PUT /course/id`: Actualiza un courso por su id.
   - `DELETE /course/id`: Elimina un curso por su id tambien las respuestas que esten relacionadas.

6. **Gestión de temas del foro**:
   - `GET /topic`: Listado de temas (paginado y ordenado).
   - `POST /topic`: Creación de un nuevo tema.
   - `GET /topic/id`: Obtiene un topico por su id.
   - `GET /topic/data`: Obtiene un topico por el nombre del curso al que pertenece y el año.
   - `PUT /topic/id`: Actualiza un topico por su id.
   - `DELETE /topic/id`: Elimina un topico por su id tambien las respuestas que esten relacionadas.
     
6. **Gestión de respuestas del foro**:
   - `GET /answer`: Listado de respuestas (paginado y ordenado).
   - `POST /answer`: Creación de un respuestacurso.
   - `GET /answer/id`: Obtiene un  respuesta por su id.
   - `PUT /answer/id`: Actualiza un respuesta por su id.
   - `DELETE /answer/id`: Elimina un respuesta por su id tambien las respuestas que esten relacionadas.
## Contribuciones

Si deseas contribuir a este proyecto:

1. Haz un fork del repositorio.
2. Crea una rama con tu funcionalidad o corrección:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Envía un pull request para revisión.

## Licencia

Este proyecto se distribuye bajo una licencia [MIT](LICENSE).

---

¡Gracias por usar la API del foro! Si tienes dudas o sugerencias, no dudes en contactarnos.

