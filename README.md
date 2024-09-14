# BIMBO/Net 
## Programa de Lealtad - Documentación [Back-end]

Desarrollo Backend que permite gestionar un programa de lealtad en BIBOMnet mediante servicios REST. 
El programa de lealtad se enfoca en la acumulación y canje de puntos por parte de los usuarios
Este proyecto contiene varios controladores que gestionan la autenticación, recompensas, puntos, canjes y usuarios
dentro del sistema de lealtad de BimboNet.

## Guía de instalación

- [Pre-requisitos](#pre-requisitos)
- [Clonar Repositorio Back-end](#clonar-repositorio-back-end)
- [Base de Datos (PostgreSQL)](#base-de-datos-postgresql)
- [BimboNet Lealtad - Controladores](#bimbonet-lealtad---controladores)
  - [AuthController](#authcontroller)
  - [RecompensaController](#recompensacontroller)
  - [PuntoController](#puntocontroller)
  - [CanjeController](#canjecontroller)
  - [UsuarioController](#usuariocontroller)
- [Dependencias](#dependencias)
- [Guía para Instalar PostgreSQL y Ejecutar un Archivo SQL](#guía-para-instalar-postgresql-y-ejecutar-un-archivo-sql)
  - [Requisitos](#requisitos)
  - [Instalación de PostgreSQL](#instalación-de-postgresql)
    - [En Windows](#en-windows)
    - [En Linux](#en-linux)
  - [Ejecutar un Archivo SQL desde la Consola de PostgreSQL](#ejecutar-un-archivo-sql-desde-la-consola-de-postgresql)

---

### Pre-requisitos

Antes de ejecutar este proyecto, asegúrate de tener instalados los siguientes componentes:

- **Java 11**: El proyecto está configurado para ejecutarse con Java 11.
  - Puedes verificar la versión instalada usando el comando:
    ```bash
    java -version
    ```
  - [Descargar Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) si no lo tienes instalado.

- **Maven 3.6+**: Maven se utiliza para la gestión de dependencias y la construcción del proyecto.
  - Verifica tu versión de Maven:
    ```bash
    mvn -version
    ```
  - [Instala Maven](https://maven.apache.org/install.html) si es necesario.

- **PostgreSQL 13+**: Este proyecto utiliza PostgreSQL como base de datos.
  - Asegúrate de que PostgreSQL esté en funcionamiento y de tener una base de datos configurada.
  - Crear una base de datos en PostgreSQL:
    ```sql
    CREATE DATABASE bimbonet_lealtad;
    ```
## Clonar Repositorio Back-end

### Clonar el repositorio

Primero, clona el repositorio del proyecto:

```bash
git clone https://github.com/cyberi0/bimbonet-lealtad.git
```

---

## Base de Datos (PostgreSQL)
- **Configuración de base de datos**: Asegúrate de configurar las credenciales de acceso a la base de datos en el archivo `application.properties`.

  `application.properties`:
  ```properties
  spring.application.name=bimbonet-lealtad
  spring.datasource.url=jdbc:postgresql://localhost:5432/bimbonet_lealtad
  spring.datasource.username=bimbonet_user
  spring.datasource.password=BimboNet
  spring.datasource.driver-class-name=org.postgresql.Driver
  server.error.whitelabel.enabled=false

---

# BimboNet Lealtad - Controladores

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
      "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjeWJlcml...",
    }
    ```
---

# RecompensaController

Este controlador gestiona las operaciones relacionadas con las recompensas.

## Endpoints

### 1. Obtener una recompensa por ID

- **GET /api/recompensas/{id}**

  - **Descripción:** Recupera los detalles de una recompensa específica utilizando su ID.
  - **Path Variable:**
    - `id` (Long): El ID de la recompensa que se desea recuperar.
  - **Response:**
    Un objeto `Recompensa` con los detalles de la recompensa.
    ```json
    {
      "id": 1,
      "nombre": "Descuento en productos",
      "descripcion": "Descuento del 10% en todos los productos",
      "valor": 10
    }
    ```

### 2. Obtener todas las recompensas

- **GET /api/recompensas/all**

  - **Descripción:** Recupera una lista de todas las recompensas disponibles.
  - **Response:**
    Una lista de objetos `Recompensa`.
    ```json
    [
      {
        "id": 1,
        "nombre": "Mediasnoche Bimbo",
        "valor": 100,
        "puntos": 1
      },
      {...},
    ]
    ```

### 3. Registrar una nueva recompensa

- **POST /api/recompensas/registrar**

  - **Descripción:** Crea una nueva recompensa en el sistema.
    - **Request Body:**
    ```json
    {
      "nombre": "Mediasnoche Bimbo",
      "valor": 100,
      "puntos" : 1
    }
    ```
  - **Response:**
    El objeto `Recompensa` de la recompensa registrada.
    ```json
    {
	    "id": 1021,
	    "nombre": "Mediasnoche Bimbo",
	    "valor": 100,
	    "puntos": 1,
	    "dateCreated": "2024-09-13T11:22:44.412081"
    }
    ```
---

# PuntoController

Este controlador gestiona las operaciones relacionadas con los puntos en el sistema de lealtad.

## Endpoints

### 1. Obtener un punto por ID

- **GET /api/puntos/{id}**

  - **Descripción:** Recupera los detalles de un punto específico utilizando su ID.
  - **Path Variable:** `id` (Long): El ID del punto que se desea recuperar.
  - **Response:** Un objeto `Punto` con los detalles del punto.
    ```json
    {
      "recompensa": {
        "id": 1021,
        "nombre": "Mediasnoche Bimbo",
        "valor": 100,
        "puntos": 1,
        "dateCreated": "2024-09-13T11:22:44.412081"
      },
      "usuarioId": 1001,
      "cantidad": 10
    }
    ```

### 2. Registrar un nuevo punto

- **POST /api/puntos/registrar**

  - **Descripción:** Crea un nuevo punto en el sistema y asocia la cantidad de puntos con la recompensa correspondiente.
  - **Request Body:**
    ```json
    {
      "recompensaId": 1004,
      "usuarioId": 1
    }
    ```
  - **Response:** El objeto `Punto` registrado con la cantidad actualizada.
    ```json
    {
      "recompensaId": {
        "id": 1004,
        "nombre": null,
        "valor": null,
        "dateCreated": null
      },
      "usuarioId": 1,
      "cantidad": 400,
      "dateCreated": "2024-09-11T13:03:57.857734"
    }
    ```

### 3. Contar puntos por usuario

- **GET /api/puntos/saldo/{usuarioId}**

  - **Descripción:** Recupera el saldo de puntos por recompensa para un usuario específico.
  - **Path Variable:** `usuarioId` (Long): El ID del usuario para el que se desea recuperar el saldo de puntos.
  - **Response:** Una lista de objetos con la cantidad de puntos agrupados por recompensa.
    ```json
    [
      {
        "recompensaId": 1,
        "cantidadPuntos": 150
      },
      {
        "recompensaId": 2,
        "cantidadPuntos": 75
      }
    ]
    ```

### 4. Contar puntos por recompensa y usuario

- **GET /api/puntos/saldo-recompensa/{usuarioId}/{recompensaId}**

  - **Descripción:** Recupera el saldo de puntos para una recompensa específica y un usuario.
  - **Path Variables:**
    - `usuarioId` (Long): El ID del usuario para el que se desea recuperar el saldo de puntos.
    - `recompensaId` (Long): El ID de la recompensa para la que se desea recuperar el saldo de puntos.
  - **Response:** Una lista de objetos con la cantidad de puntos para la recompensa y usuario especificados.
    ```json
    [
      {
        "recompensaId": 1,
        "cantidadPuntos": 100
      }
    ]
    ```

---

# UsuarioController

Este controlador gestiona las operaciones relacionadas con los usuarios en el sistema.

## Endpoints

### 1. Obtener un usuario por ID

- **GET /api/usuarios/{id}**

  - **Descripción:** Recupera los detalles de un usuario específico utilizando su ID.
  - **Path Variable:** `id` (Long): El ID del usuario que se desea recuperar.
  - **Response:** Un objeto `Usuario` con los detalles del usuario.
   ```json
     {
        "id": 11,
	    "nombreCompleto": "Juan Perez",
	    "email": "juan.perez@example.com",
	    "password": "password123",
	    "dateCreated": "2024-09-11T09:18:26.050379",
	    "puntos": [
             {
                "recompensaId": {
                    "id": 1006,
                    "nombre": "jumps over the lazy dogThe quick brown",
                    "valor": 100,
                    "puntos": 5,
                    "dateCreated": "1986-06-13T03:02:02.479"
                },
                "usuarioId": 11,
                "cantidad": 137,
                "dateCreated": "1986-08-26T07:15:36.499"
            },
            {
			    "recompensaId": {
				    "id": 1009,
				    "nombre": "quick brown fox jump",
				    "valor": 400,
				    "puntos": 2,
				    "dateCreated": "2014-12-17T23:57:01.448"
			    },
			    "usuarioId": 11,
			    "cantidad": 408,
			    "dateCreated": "2023-03-21T16:17:31.817"
		    }
	    ],
	    "enabled": true,
	    "roles": []
    }
    ```

### 2. Obtener todos los usuarios

- **GET /api/usuarios/all**

  - **Descripción:** Recupera la lista completa de usuarios registrados en el sistema.
  - **Response:** Una lista de objetos `Usuario`.
    ```json
    [
     {
        "id": 11,
	    "nombreCompleto": "Juan Perez",
	    "email": "juan.perez@example.com",
	    "password": "password123",
	    "dateCreated": "2024-09-11T09:18:26.050379",
	    "puntos": [
             {
                "recompensaId": {
                    "id": 1006,
                    "nombre": "jumps over the lazy dogThe quick brown",
                    "valor": 100,
                    "puntos": 5,
                    "dateCreated": "1986-06-13T03:02:02.479"
                },
                "usuarioId": 11,
                "cantidad": 137,
                "dateCreated": "1986-08-26T07:15:36.499"
            },
            {
			    "recompensaId": {
				    "id": 1009,
				    "nombre": "quick brown fox jump",
				    "valor": 400,
				    "puntos": 2,
				    "dateCreated": "2014-12-17T23:57:01.448"
			    },
			    "usuarioId": 11,
			    "cantidad": 408,
			    "dateCreated": "2023-03-21T16:17:31.817"
		    }
	    ],
	    "enabled": true,
	    "roles": []
    },
    {
        "id": 11,
	    "nombreCompleto": "Juan Perez",
	    "email": "juan.perez@example.com",
	    "password": "password123",
	    "dateCreated": "2024-09-11T09:18:26.050379",
	    "puntos": [
             {
                "recompensaId": {
                    "id": 1006,
                    "nombre": "jumps over the lazy dogThe quick brown",
                    "valor": 100,
                    "puntos": 5,
                    "dateCreated": "1986-06-13T03:02:02.479"
                },
                "usuarioId": 11,
                "cantidad": 137,
                "dateCreated": "1986-08-26T07:15:36.499"
            },
            {
			    "recompensaId": {
				    "id": 1009,
				    "nombre": "quick brown fox jump",
				    "valor": 400,
				    "puntos": 2,
				    "dateCreated": "2014-12-17T23:57:01.448"
			    },
			    "usuarioId": 11,
			    "cantidad": 408,
			    "dateCreated": "2023-03-21T16:17:31.817"
		    }
	    ],
	    "enabled": true,
	    "roles": []
     }
    ]
    ```

### 3. Registrar un nuevo usuario

- **POST /api/usuarios/registrar**

  - **Descripción:** Registra un nuevo usuario en el sistema.
  - **Request Body:**
    ```json
    {
      "nombreCompleto": "Cyberio Lopez",
      "email": "cyberio@bimbonet.com",
      "password": "bimbonet"
    }
    ```
  - **Response:** El objeto `Usuario` registrado.
    ```json
    {
	    "id": 15,
	    "nombreCompleto": "Cyberio Memelas",
	    "email": "cyberio@bimbonet.com.mx.ls",
	    "password": "$2a$10$vSgPoBG93BGOGR35WXXUJOxfz0bLe8A9UNtnvdB1XMxMI78wBygF.",
	    "dateCreated": "2024-09-13T13:23:45.983241",
	    "puntos": null,
	    "enabled": false,
	    "roles": null
    }
    ```
---

# CanjeController

Este controlador gestiona las operaciones de canje de puntos en el sistema de lealtad.

## Endpoints

### 1. Obtener un ejemplo de punto por ID

- **GET /api/canje/{id}**

  - **Descripción:** Devuelve un ejemplo de punto con un valor de 100 puntos. Este endpoint es para propósitos de prueba.
  - **Path Variable:** `id` (Long): El ID proporcionado por el usuario, pero no es utilizado en el procesamiento real.
  - **Response:** Un objeto `Punto` con 100 puntos, asociado a una recompensa y usuario ficticios.
    ```json
    {
      "recompensa": {
        "id": null,
        "nombre": null,
        "valor": null,
        "dateCreated": null
      },
      "usuarioId": null,
      "cantidad": 100
    }
    ```

### 2. Canjear puntos por una recompensa

- **POST /api/canje/puntos**

  - **Descripción:** Permite a un usuario canjear puntos por una recompensa específica. Verifica si el usuario tiene suficientes puntos acumulados para realizar el canje.
  - **Request Body:**
    ```json
    {
      "usuarioId": 1,
      "recompensaId": 1004
    }
    ```
  - **Response (éxito):** El objeto `UsuarioRecompensa` después de realizar el canje de puntos.
    ```json
    {
      "usuarioId": 1,
      "recompensaId": 1004,
      "valor": 400
    }
    ```
  - **Response (error):** Un mensaje de error indicando que faltan puntos para realizar el canje.
    ```json
    {
      "error": "Lo sentimos... Te faltan 50 puntos para canjear tu recompensa 'Mediasnoche Bimbo'."
    }
    ```

---
## Dependencias

- **Spring Boot** para la construcción del backend.
- **Spring Security** para autenticación y autorización.
- **JWT** para la gestión de tokens.

---

# Guía para Instalar PostgreSQL y Ejecutar un Archivo SQL

## Requisitos

- Sistema operativo: Windows, macOS o Linux.
- Acceso a la consola del sistema.

## Instalación de PostgreSQL

### En Windows

1. **Descargar PostgreSQL:**
  - Ve al [sitio oficial de PostgreSQL](https://www.postgresql.org/download/windows/).
  - Descarga el instalador de la versión más reciente para Windows.

2. **Ejecutar el Instalador:**
  - Ejecuta el archivo `.exe` descargado.
  - Sigue las instrucciones del asistente de instalación.
  - Asegúrate de instalar también `pgAdmin` si deseas una interfaz gráfica para la administración.

3. **Verificar la Instalación:**
  - Abre la consola de comandos (cmd) y ejecuta:
    ```sh
    psql --version
    ```
  - Debes ver la versión de PostgreSQL instalada.

### En Linux

1. **Usar el Gestor de Paquetes:**
  - Abre la terminal y ejecuta uno de los siguientes comandos según tu distribución:

    **Para Debian/Ubuntu:**
    ```sh
    sudo apt update
    sudo apt install postgresql postgresql-contrib
    ```

2. **Iniciar el Servicio de PostgreSQL:**
  - Para Debian/Ubuntu:
    ```sh
    sudo systemctl start postgresql
    ```

3. **Verificar la Instalación:**
  - Ejecuta:
    ```sh
    psql --version
    ```
  - Muestra la versión de PostgreSQL instalada.

## Ejecutar un Archivo SQL desde la Consola de PostgreSQL

1. **Acceder a la Consola de PostgreSQL:**
  - Abre la consola de comandos o terminal.
  - Ejecuta el siguiente comando para acceder a la consola de PostgreSQL:
    ```sh
    psql -U <nombre_usuario> -d <nombre_base_datos>
    ```
    Reemplaza `<nombre_usuario>` con el nombre de usuario de PostgreSQL y `<nombre_base_datos>` con el nombre de la base de datos en la que deseas ejecutar el archivo SQL.

2. **Ejecutar el Archivo SQL:**
  - Una vez dentro de la consola de PostgreSQL, ejecuta el siguiente comando para ejecutar el archivo SQL:
    ```sql
    \i /ruta_proyecto/src/main/resources/schema/bimbonet_lealtad.sql
    ```
---

## Ejecutar Proyecto Spring Boot

1. **Acceder a ruta del proyecto mediante la Terminal:**
  - Ejecuta el siguiente comando para Instalar las Dependencias:
    ```sh
    mvn clean install
    ```
    Resultado del Comando
    ```
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  3.074 s
    [INFO] Finished at: 2024-09-13T23:19:09-06:00
    [INFO] ------------------------------------------------------------------------
    ```

2. **Ejecutar Proyecto**
- Una vez dentro de la consola, ejecuta el siguiente comando ejecutar el Proyecto:
  ```sh
  mvn spring-boot:run 
  ```
  Resultado del comando
  ```
  [INFO] Attaching agents: []

  .   ____          _            __ _ _
  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
  ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
  =========|_|==============|___/=/_/_/_/

  :: Spring Boot ::                (v3.3.3)

  2024-09-13T23:19:58.294-06:00  INFO 39965 --- [bimbonet-lealtad] 
  2024-09-13T23:19:58.295-06:00  INFO 39965 --- [bimbonet-lealtad]
  2024-09-13T23:19:58.506-06:00  INFO 39965 --- [bimbonet-lealtad] 
  [main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
  ...
  ...
  ...
  2024-09-13T23:20:00.266-06:00  INFO 39965 --- [bimbonet-lealtad] 
  [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
  2024-09-13T23:20:00.271-06:00  INFO 39965 --- [bimbonet-lealtad] 
  [main] c.b.b.BimbonetLealtadApplication : Started BimbonetLealtadApplication in 2.128 seconds (process running for 2.274)

  ```
  - Si requieres ejecutar el Proyecto en modo Debbug, ejecuta el siguiente comando:
  ```sh
    mvn spring-boot:run -X
  ```
    
3. **Ejecutar Pruebas Unitarias**
    ```sh
    mvn test
    ``` 
    Resultado de las Pruebas Unitarias
    ```
    [INFO] 
    [INFO] --- surefire:3.2.5:test (default-test) @ bimbonet-lealtad ---
    [INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
    [INFO]
    [INFO] -------------------------------------------------------
    [INFO]  T E S T S
    [INFO] -------------------------------------------------------
    [INFO] Running CanjeControllerTest
    Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
    23:25:41.037 [main] INFO com.bimbonet.bimbonet_lealtad.Controllers.CanjeController -- valorRecompensa - 100
    23:25:41.039 [main] INFO com.bimbonet.bimbonet_lealtad.Controllers.CanjeController -- valorAcumuladoRecompensa - 150
    [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.700 s -- in CanjeControllerTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Controllers.UsuarioControllerTest
    [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.069 s -- in com.bimbonet.bimbonet_lealtad.Controllers.UsuarioControllerTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Controllers.RecompensaControllerTest
    [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.016 s -- in com.bimbonet.bimbonet_lealtad.Controllers.RecompensaControllerTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Controllers.PuntoControllerTest
    [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s -- in com.bimbonet.bimbonet_lealtad.Controllers.PuntoControllerTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Services.CanjeServiceTest
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s -- in com.bimbonet.bimbonet_lealtad.Services.CanjeServiceTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Services.UsuarioServiceTest
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s -- in com.bimbonet.bimbonet_lealtad.Services.UsuarioServiceTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Services.PuntoServiceTest
    [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.006 s -- in com.bimbonet.bimbonet_lealtad.Services.PuntoServiceTest
    [INFO] Running com.bimbonet.bimbonet_lealtad.Services.RecompensaServiceTest
    [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 s -- in com.bimbonet.bimbonet_lealtad.Services.RecompensaServiceTest
    [INFO]
    [INFO] Results:
    [INFO]
    [INFO] Tests run: 19, Failures: 0, Errors: 0, Skipped: 0
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  2.425 s
    [INFO] Finished at: 2024-09-13T23:25:41-06:00
    [INFO] ------------------------------------------------------------------------

    ``` 
---