# Resource Server

Este es un proyecto desarrollado por ing. Daniel Larín el cual consiste en un resource server el cual
trabaja con OAuth2, de igual manera si se quiere trabajar con JWS, se debe descomentar ciertos métodos alojados en
el paquete filter y authorization

## Descripción

Este proyecto incluye una API RESTful protegida con OAuth2 el cual se comunica con
el authorization-server y así asegurando la información

## Instalación

A continuación se detalla cómo instalar y configurar el proyecto.

### Prerrequisitos

- Java 17
- Maven
- Git

### Pasos de instalación

1. Clona el repositorio: https://github.com/CL17-I04-002/SpringSecurity6.git
2. Realice un Reload project para que se descarguen todas las dependencias
3. Compile la solución y listo

### NOTA
Recuerde que en este proyecto se puede ejecutar en 3 ambientes (dev, local y docker)
si desea ejecutar dicho ambiente, dirigase al application.properties y cambie la propiedad
spring.profiles.active ya sea en dev, local o en docker (recuerde que debe tener montado el contenedor para que el proyecto se ejecute correctamente)