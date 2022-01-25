<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">OpenAPI Code Generation</h3>

  <p align="center">
    This project demonstrates how to generate both a server and client from a given
    OpenAPI 3 specification using the API-first development approach.
    <br />
  </p>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project

This application demonstrates an API-first approach for rapidly building REST APIs. This approach assumes
the design and development of an application programming interface comes before the implementation. After the API interface
has been defined, the team relies on it to build the rest of the application.

The following describes some advantages to this approach.

* Development teams can work in parallel
* API definitions can be shared between teams before implementation is complete
* Code generation speeds up development process

We will learn the steps of how to design a REST API using the OpenAPI 3 specification and generate both a client (Feign)
and server (Spring Boot REST) from it.

### Built With


* [OpenAPI 3 Specification](https://swagger.io/specification/)
* [Spring Boot](https://spring.io/)
* [Apache Maven](https://spring.io/)
* [OpenAPI Generator Maven Plugin](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin)


<!-- GETTING STARTED -->
## Getting Started

Clone the project and open it in an IDE of your choosing. You will find the OpenAPI 3 specifications under the
following path.

  ```sh
  src/main/resources/openapi/client
  src/main/resources/openapi/server
  ```

The specifications have been taken from the openly available [Petstore example project](https://petstore3.swagger.io/).


<!-- USAGE EXAMPLES -->
## Usage

The following maven commands are used to generate the client and server with the help of the OpenAPI Generator Maven plugin.

Client (Webclient)
  ```sh
  mvn clean generate-sources -P openapi-client-webclient
  ```

Client (Feign)
  ```sh
  mvn clean generate-sources -P openapi-client-feign
  ```

Server (Spring Boot REST)
  ```sh
  mvn clean generate-sources -P openapi-server
  ```

The generated classes can be found under the following path

  ```sh
  target/generated-sources/main/java/com.github.pwnmn/**
  ```

After starting the application, you can access the generated server using Swagger UI
under the following URL.

  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Peter Weismann | pweismann@palo-it.com