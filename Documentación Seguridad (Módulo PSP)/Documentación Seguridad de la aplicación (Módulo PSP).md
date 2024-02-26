# Seguridad en la API

## Introducción

Este documento describe las medidas de seguridad implementadas en la API para proteger tanto la autenticación de los usuarios como la transmisión de información, utilizando roles específicos y algoritmos criptográficos. 

## Autenticación y Autorización

La seguridad de la API se basa en el uso de JWT (JSON Web Tokens) para manejar la autenticación y la autorización de los usuarios. Spring Security se ha configurado para integrar estos tokens en el proceso de autenticación, utilizando SHA-512 como algoritmo de firma para garantizar la integridad y la seguridad del token.

### Proceso de Autenticación

- **Endpoint `/login`**: Los usuarios se autentican mediante el envío de sus credenciales (nombre de usuario y contraseña) a este endpoint.
- **Generación de Token**: Si las credenciales son válidas, se genera un JWT, firmado con el algoritmo SHA-512, que contiene información del usuario y permisos basados en roles.
- **Envío del Token**: El token se envía de vuelta al usuario como parte de la respuesta de autenticación.

### Proceso de Autorización

- **Filtro de JWT**: Cada solicitud a la API debe incluir el JWT en el encabezado `Authorization`. Un filtro personalizado (`JwtTokenFilter`) extrae y valida el token.
- **Validación de Token**: Si el token es válido, se extrae la información del usuario y se establece el contexto de seguridad para la sesión.

## Encriptación de Contraseñas

Las contraseñas de los usuarios se encriptan utilizando `BCryptPasswordEncoder` antes de almacenarse en la base de datos. Esto asegura que las contraseñas almacenadas no estén en texto plano.

## Seguridad de los Canales de Comunicación

Aunque se han tomado varias medidas de seguridad a nivel de aplicación, es crucial implementar SSL/TLS para asegurar los canales de comunicación. Esta implementación garantizaría que todos los datos transmitidos entre el cliente y el servidor estén cifrados y protegidos contra interceptaciones.

### Medidas Necesarias

- **HTTPS**: Es esencial configurar el servidor para aceptar solo conexiones HTTPS, asegurando así que la comunicación entre el cliente y el servidor esté cifrada.
- **Certificados SSL/TLS**: Se deben utilizar certificados válidos emitidos por una Autoridad de Certificación confiable para el cifrado de la comunicación.

## Permisos Basados en Roles

La autorización se maneja a nivel de endpoints utilizando anotaciones de Spring Security como `@PreAuthorize`, permitiendo así controlar el acceso basado en los roles de los usuarios.

## Conclusión

Las estrategias de seguridad implementadas en nuestra API, incluyendo la autenticación mediante JWT con firma SHA-512, la encriptación de contraseñas con `BCryptPasswordEncoder`, y la gestión de permisos basada en roles, proporcionan un entorno robusto y seguro para la protección de los datos y la privacidad de los usuarios. Estas medidas aseguran que tanto la autenticación de usuarios como la transmisión de información se realicen de manera segura y eficiente.
