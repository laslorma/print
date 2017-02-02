# ernesto-spring-template

My template when starting a spring project (spring boot, JPA, Hibernate, Mysql)

## Pre-requisites

You need to have the following installed on your computer before installing "ernesto-spring-template":

- git
- Maven 3.0+
- mysql
- phpMyAdmin
- JDK 1.8 or later

###
## Instructions
### Download the code

``` shell
git clone https://github.com/laslorma/print.git
cd print
```

### Database

Ejecutar el script en Mysql

    print/BD/catwizzard_master.sql
    
###
## Configuration

### Data Base 

#### Upload the database

Colocar la base de datos, usuario y password en el archivo 

   src/main/resources/application.properties
   
Linea

```
spring.datasource.url = jdbc:mysql://localhost:3306/NOMBRE_DE_BD
```

Y usuario y password en

```
spring.datasource.username = "USER"
spring.datasource.password = "PASSWORD"
```

### Running ernesto-spring-template

```
C:\Users\Catw\Documents>cd print
C:\Users\Catw\Documents\print>mvn package
C:\Users\Catw\Documents\print>java -jar target/gs-rest-service-0.1.0.jar
```
Por medio de la consola se corre el proyecto. En primer lugar se posiciona en la carpeta del proyecto, una vez adentro se ejecuta el comando "mvn package" para generar el empaquetado del proyecto con la configuración del mismo, despues se ejecuta el comando "java -jar target/gs-rest-service-0.1.0.jar" para correr el jar generado en el empaquetado. 
