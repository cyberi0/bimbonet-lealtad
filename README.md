# BimboNet Lealtad - Controladores

Este proyecto contiene varios controladores que gestionan la autenticación, recompensas, puntos, canjes y usuarios dentro del sistema de lealtad de BimboNet.

## Índice

- [AuthController](#authcontroller)
- [RecompensaController](#recompensacontroller)
- [PuntoController](#puntocontroller)
- [CanjeController](#canjecontroller)
- [UsuarioController](#usuariocontroller)

---

## AuthController

Este controlador gestiona la autenticación de usuarios y la generación de tokens JWT.

### Endpoints:

- **POST /auth/security/login**

  - Descripción: Autentica al usuario y genera un token JWT.
  - Request Body:
    ```json
    {
      "email": "usuario@example.com",
      "password": "password"
    }
    ```
  - Response: Un objeto `JwtResponse` con el token generado.
    ```json
    {
      "token": "eyJhbGciOiJIUzUxMiJ9..."
    }
    ```

---

## RecompensaController

Este controlador gestiona las recompensas en el sistema.

### Endpoints:

- **GET /api/recompensas/{id}**

  - Descripción: Obtiene una recompensa por su ID.
  - Path Variable:
    - `id`: El ID de la recompensa.
  - Response: Objeto `Recompensa`.

- **POST /api/recompensas/registrar**

  - Descripción: Registra una nueva recompensa.
  - Request Body: Un objeto `Recompensa`.
  - Response: La recompensa registrada.

---

## PuntoController

Este controlador gestiona los puntos asociados a los usuarios y recompensas.

### Endpoints:

- **GET /api/puntos/{id}**

  - Descripción: Obtiene la información de un punto dado su ID.
  - Path Variable:
    - `id`: El ID del punto.
  - Response: Un objeto `Punto`.

- **POST /api/puntos/registrar**

  - Descripción: Registra un nuevo punto para un usuario.
  - Request Body: Un objeto `Punto`.
  - Response: El punto registrado.

- **GET /api/puntos/saldo/{usuarioId}**

  - Descripción: Calcula la cantidad de puntos agrupados por recompensa para un usuario específico.
  - Path Variable:
    - `usuarioId`: El ID del usuario.
  - Response: Una lista de puntos agrupados por recompensa.

- **GET /api/puntos/saldo-recompensa/{usuarioId}/{recompensaId}**

  - Descripción: Calcula la cantidad de puntos de un usuario para una recompensa específica.
  - Path Variables:
    - `usuarioId`: El ID del usuario.
    - `recompensaId`: El ID de la recompensa.
  - Response: Una lista de puntos agrupados por recompensa.

---

## CanjeController

Este controlador gestiona el canje de puntos por recompensas.

### Endpoints:

- **GET /api/canje/{id}**

  - Descripción: Obtiene la información de un punto asociado a un canje por su ID.
  - Path Variable:
    - `id`: El ID del punto.
  - Response: Un objeto `Punto`.

- **POST /api/canje/puntos**

  - Descripción: Realiza el canje de puntos de un usuario por una recompensa.
  - Request Body: Un objeto `UsuarioRecompensa`.
    ```json
    {
      "usuarioId": 1,
      "recompensaId": 2
    }
    ```
  - Response: El resultado del canje o un mensaje de error si no hay suficientes puntos.
    ```json
    {
      "error": "Lo sentimos... Te faltan 50 puntos para canjear tu recompensa."
    }
    ```

---

## UsuarioController

Este controlador gestiona la información de los usuarios en el sistema.

### Endpoints:

- **GET /api/usuarios/{id}**

  - Descripción: Obtiene la información de un usuario por su ID.
  - Path Variable:
    - `id`: El ID del usuario.
  - Response: Un objeto `Usuario`.

- **GET /api/usuarios/all**

  - Descripción: Obtiene la lista de todos los usuarios registrados.
  - Response: Una lista de objetos `Usuario`.

- **POST /api/usuarios/registrar**

  - Descripción: Registra un nuevo usuario en el sistema.
  - Request Body: Un objeto `Usuario`.
  - Response: El usuario registrado.

---

## Dependencias

- **Spring Boot** para la construcción del backend.
- **Spring Security** para autenticación y autorización.
- **JWT** para la gestión de tokens.
