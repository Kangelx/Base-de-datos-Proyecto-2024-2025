# Implementación de Seguridad en la Aplicación API con SHA-512

Este documento detalla la implementación de seguridad en la aplicación API, incluyendo la autenticación de usuarios, la autorización basada en roles, y la protección de la información transmitida utilizando algoritmos criptográficos como SHA-512 para la firma digital.

## Autenticación y Autorización

La aplicación implementa un sistema de autenticación y autorización basado en tokens JWT y utiliza SHA-512 como parte del algoritmo de firma para garantizar la integridad y autenticidad del token.

### Componentes Principales

- **`AuthController`**: Gestiona las operaciones de autenticación y registro.
- **`AuthTokenResponse`**: Encapsula la respuesta de token de autenticación.
- **`CustomUserDetailsService`**: Carga los detalles del usuario por nombre de usuario.
- **`JwtTokenFilter`**: Valida el token JWT en cada solicitud.
- **`JwtTokenUtil`**: Genera y valida tokens JWT, utilizando SHA-512 para la firma.

### Proceso de Autenticación

1. **Login**:
   - Autenticación mediante `AuthenticationManager`.
   - Generación de token JWT firmado con SHA-512.

2. **Registro**:
   - Creación y almacenamiento seguro de nuevos usuarios.

### Autorización basada en Roles

- Restricción de acceso a recursos mediante anotaciones `@PreAuthorize`.

## Seguridad de la Comunicación

### Configuración de Seguridad con `SecurityConfig`

- Deshabilitación de CSRF.
- Establecimiento de política de sesiones sin estado.
- Configuración de rutas accesibles sin autenticación.
- Incorporación de `JwtTokenFilter` para la validación de tokens.

### Firma de Tokens JWT con SHA-512

La firma de tokens JWT se realiza con el algoritmo SHA-512, proporcionando una capa adicional de seguridad que garantiza que los tokens no han sido alterados.

## Almacenamiento Seguro de Contraseñas

Las contraseñas se almacenan de forma segura utilizando codificación bcrypt, evitando almacenar contraseñas en texto plano.

## Implementación de Algoritmos Criptográficos Adicionales

Aunque la firma SHA-512 proporciona una medida de seguridad robusta para la autenticación de tokens, se recomienda evaluar la necesidad de implementar algoritmos criptográficos adicionales como DES, RSA o MD5 para:

- Cifrado de la comunicación entre cliente y servidor.

## Asegurar los Canales de Comunicación

Se debe asegurar que todos los canales de comunicación utilizan HTTPS para proteger la información transmitida entre el cliente y el servidor. Esto se puede configurar a nivel de servidor web o aplicación.

## Auditoría y Registro de Actividades

Se recomienda implementar un sistema de auditoría para registrar actividades sensibles y cambios en la configuración de seguridad, permitiendo la detección temprana de posibles brechas de seguridad.

