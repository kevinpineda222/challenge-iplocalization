# Challenge-IP-Localization

Desafio tecnico de Mercado Libre para localizar un pais mediante su IP, ademas de consulta estadistica de distancias desde Buenos Aires hacia el pais mas lejano, cercano y un promedio en KM de las distancias segun la cantidad de invocaciones

El primer response debera mostrar :

* El nombre y código ISO del país
* Los idiomas oficiales del país
* Hora(s) actual(es) en el país (si el país cubre más de una zona horaria, mostrar todas)
* Distancia estimada entre Buenos Aires y el país, en km.
* Moneda local, y su cotización actual en dólares (si está disponible) 

De resto, segun las invocaciones anteriores se podra consultar:

* Distancia más lejana a Buenos Aires desde la cual se haya consultado el servicio
* Distancia más cercana a Buenos Aires desde la cual se haya consultado el servicio
* Distancia promedio de todas las ejecuciones que se hayan hecho del servicio.

## Comenzando 🚀

El proyeto podra ejecutarse localmente o ejecutando un contenedor de Docker

### Pre-requisitos 📋

Se debe tener instalado Gradle y JDK

### Instalación 🔧

Basta con ejecutar las siguientes lineas en consola

```
git clone https://github.com/kevinpineda222/challenge-iplocalization.git
cd challenge-iplocalization
gradle clean
gradle bootrun
```

En caso de querer utilizar el docker, luego de clonar el repositorio debera ejecutar las siguientes lineas :

```
docker build -t iplocalization:1.0.0 .
docker run -d -p 8085:8085 --name iplocalization iplocalization:1.0.0
```

## Ejecución ⚙️

Tanto para la ejecucion local como para ejecutar el contenedor de docker, se ha expuesto la siguiente API en Swagger

* [API-IP-LOCALIZATION](http://localhost:8085/swagger-ui.html#/) - API DOCUMENTATION SWAGGER


## Herramientas Utilizadas 🛠️

Se utilizaron las siguientes herramientas para el desarrollo :

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Gradle](https://gradle.org/)
* [Docker](https://www.docker.com/)


## Autor ✒️

**Kevin Pineda**
* [Linkedin](https://www.linkedin.com/in/kevin-pineda-05a30b19a?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BZCWMjLqKTfeD5SDA05jlKQ%3D%3D)
