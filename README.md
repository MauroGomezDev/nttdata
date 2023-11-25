# nttdata
Repositorio evaluacion NTTDATA

# Proyecto de Microservicio de Creación de Usuarios

Este proyecto de microservicio fue desarrollado para aplicar a una vacante de desarrollador para la empresa NTTDATA.

El proyecto base fue creado en el sitio https://start.spring.io/

## Funcionalidad
Proporciona funcionalidades para la creación y consulta de usuarios, con validación de datos y manejo de excepciones. 
El servicio persiste los datos en una base de datos H2 y utiliza tokens JWT para la posterior autenticación.

## Requisitos

- Java 17
- Maven 3.8.1
- Springboot 3.1.5
- Dependencias de Spring Boot (Spring Web, Spring Data JPA, Spring Security lombok, jaxb, jackson, etc)

## Configuración

1. Clona el repositorio desde [GitHub](https://github.com/tu-usuario/tu-repo) o descarga el código fuente.

2. Asegúrate de tener Java 17 instalado en tu sistema.

3. Edita el archivo `application.properties` para configurar la base de datos y otras propiedades según tus necesidades.
   Actualmente tiene configurada una base de datos H2.

## Ejecución

Para ejecutar el proyecto. Abre una terminal en el directorio raíz del proyecto y ejecuta el siguiente comando:

Terminal cmd: 
java -jar evaluacion-0.0.1-SNAPSHOT.jar


** Si el comando se ejecuta correctamente debe mostrar un los similar al siguiente:
2023-11-25T02:41:05.129-03:00  WARN 32140 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2023-11-25T02:41:05.605-03:00  INFO 32140 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-11-25T02:41:05.629-03:00  INFO 32140 --- [           main] c.n.evaluacion.EvaluacionApplication     : Started EvaluacionApplication in 6.374 seconds (process running for 7.113)

La aplicación se ejecutará en http://localhost:8080.

## Endpoints
post http://localhost:8080/api/evaluacion/create-usr   : Creacion de usuario

get http://localhost:8080/api/evaluacion/list          : Lista usuarios registrados

get http://localhost:8080/api/evaluacion/get-usr-by-email {"email": "mgomez@gmail.com"} : Lee un usuario por mail pasado como parametro formato json

put http://localhost:8080/api/evaluacion/update-usr/mgomez@gmail.com : Modifica un registro de usuario

delete http://localhost:8080/api/evaluacion/delete-usr/mgomez@gmail.com : elimina un usuario por su correo

patch http://localhost:8080/api/evaluacion/update-usr/mgomez@gmail.com : Modifica un registro del usuario (fecha ultimo login)

Para la creacion de usuarios, la aplicacion solo permite formato JSON. 
Validará el formato del correo electrónico y la contraseña. 
Retorna un usuario con los campos id, created date, modified date, lastLogin, token y isActive.

Uso
Puedes utilizar herramientas como Postman para probar los endpoints del servicio.

Ejemplo de solicitud POST para crear un usuario:

POST http://localhost:8080/sign-up
{
  "name": "Lina Gomez",
  "email": "lgomez@gmail.com",
  "password": "Qwerty12",
  "phones": [
    {
      "number": 123456789,
      "citycode": 8320000,
      "countrycode": "SCL"
    }
  ]
}
** La respuesta de este servisio es un Httpstatus + un json con la siguiente estructura
{"id":"c12cb258-d9e2-4d99-864c-f817b3f9c014","created":[2023,11,11],"lastLogin":[2023,11,11],"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjMTJjYjI1OC1kOWUyLTRkOTktODY0Yy1mODE3YjNmOWMwMTQiLCJuYW1lIjoiTWF1cm8gR29tZXoiLCJleHAiOjE2OTk2NzE2MDAsImlhdCI6MTY5OTY3MTYwMCwiZW1haWwiOiJtZ29tZXpAZ21haWwuY29tIn0.3HfUrO94V-Jx2-Pmg-cXcKJt97doMRlA1SGR_G_B60Y","active":true}

** En caso de error el json de respuesta es el siguiente:
{"mensaje":"Mensaje de error"}

## Diagramas
Se creo un diagrama de secuencia que encuentra en este mismo repositorio.

## Contribución
Si deseas contribuir a este proyecto, por favor abre un problema o envía una solicitud de extracción.

## Licencia
N/A

## "Pruebas unitarias" 
Se desarrollaron 2 tes unitarios y se utilizo junit y mockito
- EvaluacionControlletTests.java
- UserServiceImplTest.java






