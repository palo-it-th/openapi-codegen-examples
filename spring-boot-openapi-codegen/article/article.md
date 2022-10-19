# Spring Boot: Client- and server code generation using Openapi 3 Specs 

REST APIs are ubiquitous in today's world ranging from payments, communication and banking. Developers can plug-in virtually any specialist service they require for their applications.

So it comes as no suprpise that we at Palo IT are frequently developing APIs for our customers and integrating external APIs with our backend applications.
Both, our own APIs and external ones, can become rather big and extensive, making it time consuming to write them all by hand.
Luckily, there are some nifty techniques to make our lifes as developers easier and avoid writing repetitive code.

Enter OpenApi 3 specs and code generation!

Imagine someone needs to access our freshly developed API.
In the past we would share our endpoint definitions using a non-standardized way such as Excel sheets.
The consumers of our API would have to implement a client within their application.
With large APIs, this can take quite a while. In order to not re-invent the wheel everytime, the openapi spec was created.
It enables us to define our REST API contract in a standardized format and easily share it with others.

Thus, an ecosystem of editors and code generation tools were created around the openapi specification of which the latest version is 3.1 at the time of writing.
These tools allow us to create API documentation and generate both clients and servers for a vast multitude of programming languages and frameworks.
This article will focus on utilizing code generation with the popular Java [Spring Boot framework](https://spring.io/projects/spring-boot) which is used in a few of our projects.

## Definitions

Before we jump into the code, some definitions have to be established to fully understand all the terms.

### Openapi specification

The following is a short explanation of what an openapi specification is. In short, it is an open API description format and enables us to easily share definitions of a REST API.


> The [OpenAPI Specification](https://swagger.io/specification/) (OAS) defines a standard, language-agnostic interface to RESTful APIs which allows
> both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection.
> When properly defined, a consumer can understand and interact with the remote service with a minimal amount of implementation logic.

### Client code

We will refer to client code as any client SDK that enables us to call a remote REST API. In the context of Spring Boot,
client code is usually written using HTTP clients such as the [Rest Template](https://docs.spring.io/spring-android/docs/current/reference/html/rest-template.html) or
the more recently introduced [Webclient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client).

### Server code

By Server code we will refer to any code necessary to create a REST API server. Using Spring Boot, this is usually accomplished
with the servlet-api based [Spring Web](https://spring.io/guides/tutorials/rest/) or the newer, non-blocking [Spring Webflux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html).

### Code generation

The code generation we will learn about in this tutorial will be done using the [OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator).
It is a community project widely used by many well-known companies and can generate client code or server stubs from an openapi spec in a [multitude of programming languages and frameworks](https://github.com/OpenAPITools/openapi-generator#overview). There are many ways to use the generator.
It comes as a maven plugin, npm module and standalone JAR. In the following sections we will utilize the maven plugin for convenience since the Spring Boot project
uses it as the build management tool.

On a side note, the OpenAPI Generator was forked from the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen) to simplify the original and enable stronger community ownership. 

## Tutorial Scope

With this tutorial, we will demonstrate how to generate both server-side and client-side code from an Openapi 3 specification. The v3 specification
is the latest Openapi iteration and extends the older v2 version with numerous new [functionalities and improvements](https://blog.stoplight.io/difference-between-open-v2-v3-v31).

For simplicity's sake, the [Swagger Petstore](https://petstore.swagger.io/) will be used for the generation. It is a simple example REST API demonstrating the capabilities of
the Openapi specification including authorization, HTTP schemes and API descriptions.

### Server

For the server-side, the generator will output traditional Spring Web based code.

### Client

To illustrate the client code generation, the reactive WebClient will be used. For our projects,
we at Palo IT prefer to use the Webclient because it simplifies performing concurrent calls compared to the Rest Template.
Also, the RestTemplate is no longer in active development and remains in [maintenance mode](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html). Thus, the WebClient should be preferred
for new projects.

## Prerequisites

Before getting started, we need to ensure all necessary tools to run the application are available. The tools and installation
instruction are below.

- [JDK 11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
- [Cloned Git Repository](https://github.com/PaloITThailand/openapi-codegen-examples)

## Project structure
To become familiar with the components and structure of the project, the main areas of interest are depicted below.

The Petstore Openapi specification in YAML format is kept in the `src/main/resources/openapi` folder.
Both server and client contain the same spec but have been kept in separate folders for simplicity.
```
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── paloit
    │   │           ├── client
    │   │           │   └── petstore
    │   │           │       └── webclient ->    1. Generated webclient
    │   │           │           ├── api
    │   │           │           ├── apiclient
    │   │           │           ├── auth
    │   │           │           └── model
    │   │           ├── config ->               2. General project configuration
    │   │           └── server
    │   │               └── petstore ->         3. Generated server
    │   │                   ├── api
    │   │                   ├── controller
    │   │                   └── model
    │   └── resources
    │       ├── generator-template-overrides -> 4. Generation template adjustment
    │       │   └── webclient
    │       │       └── auth
    │       └── openapi
    │           ├── client ->                   5. Client - Petstore Openapi Spec
    │           └── server ->                   6. Server - Petstore Openapi Spec
    └── test
        └── java
            └── com
                └── paloit ->                   7. Client and Server Tests
                    ├── client
                    │   └── petstore
                    │       └── webclient
                    └── server
                        └── petstore
                            └── controller
```

1. The generated Webclient code
2. Common configuration for the Webclient, SpringDoc API documentation and Jackson parser
3. The generated Spring Web server stubs
4. Adjustment for the generation templates. Sometimes custom adjustments are needed for the generation process if the
   default templates are not sufficient. More about this in [Custom generator templates](#custom-generator-templates)
5. Tests for both generated server and client

The configuration for the OpenAPI Generator can be found in the pom file. To separate configuration for the client-
and server generator, maven profiles have been used as seen below.

### Spring Web server generator configuration

```xml
<profile>
   <!-- Name of the maven profile for server stub generator-->
   <id>openapi-server</id>
   <activation>
       <property>
           <name>generate</name>
           <value>swagger</value>
       </property>
   </activation>
   <build>
       <plugins>
           <plugin>
               <groupId>org.openapitools</groupId>
               <artifactId>openapi-generator-maven-plugin</artifactId>
               <version>${version.openapi-generator}</version>
               <executions>
                   <execution>
                       <id>generate-code</id>
                       <goals>
                           <goal>generate</goal>
                       </goals>
                       <configuration>
                           <!-- Spring generator config -->
                           <generatorName>spring</generatorName>
                           <library>spring-boot</library>
                           <!-- Reference to the Petstore spec -->
                           <inputSpec>${project.basedir}/src/main/resources/openapi/server/petstore.yml</inputSpec>
                           <skipIfSpecIsUnchanged>true</skipIfSpecIsUnchanged>
                           <!-- Enable API generation -->
                           <generateApis>true</generateApis>
                           <!-- Enable API documentation generation -->
                           <generateApiDocumentation>true</generateApiDocumentation>
                           <!-- Do not generate tests -->
                           <generateApiTests>false</generateApiTests>
                           <!-- Generate models -->
                           <generateModels>true</generateModels>
                           <generateModelDocumentation>false</generateModelDocumentation>
                           <generateModelTests>false</generateModelTests>
                           <generateSupportingFiles>true</generateSupportingFiles>
                           <!-- Generation output will be in target/generated-sources -->
                           <output>${project.build.directory}/generated-sources</output>
                           <!-- Model java package name -->
                           <modelPackage>${default.package}.server.petstore.model</modelPackage>
                           <!-- API java package name --> 
                          <apiPackage>${default.package}.server.petstore.api</apiPackage>
                           <configOptions>
                               <sourceFolder>main/java</sourceFolder>
                               <!-- Use modern java8 date/time api -->
                               <dateLibrary>java8</dateLibrary>
                               <java8>true</java8>
                               <oas3>true</oas3>
                               <useSpringController>true</useSpringController>
                               <useSpringfox>false</useSpringfox>
                               <!-- Enable bean validation using javax validation and hibernate validator  -->
                               <useBeanValidation>true</useBeanValidation>
                               <performBeanValidation>true</performBeanValidation>
                               <interfaceOnly>false</interfaceOnly>
                               <!-- Use delegate pattern to separate implementation from API definition  -->
                               <delegatePattern>true</delegatePattern>
                               <useOptional>false</useOptional>
                               <!-- Place required parameters first in models  -->
                               <sortModelPropertiesByRequiredFlag>true</sortModelPropertiesByRequiredFlag>
                               <sortParamsByRequiredFlag>true</sortParamsByRequiredFlag>
                           </configOptions>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
       </plugins>
   </build>
</profile>
```

### Webclient generator configuration

```xml
<profile>
   <!-- Name of the maven profile for server stub generator-->
   <id>openapi-client-webclient</id>
   <activation>
      <property>
         <name>generate</name>
         <value>swagger</value>
      </property>
   </activation>
   <build>
      <plugins>
         <plugin>
            <groupId>org.openapitools</groupId>
            <artifactId>openapi-generator-maven-plugin</artifactId>
            <version>${version.openapi-generator}</version>
            <executions>
               <execution>
                  <id>generate-code</id>
                  <goals>
                     <goal>generate</goal>
                  </goals>
                  <configuration>
                     <!-- Webclient generator config -->
                     <generatorName>java</generatorName>
                     <library>webclient</library>
                     
                     <!-- Reference to the Petstore spec -->
                     <inputSpec>
                        ${project.basedir}/src/main/resources/openapi/client/petstore.yml
                     </inputSpec>

                     <skipIfSpecIsUnchanged>true</skipIfSpecIsUnchanged>
                     <generateApis>true</generateApis>
                     <generateApiDocumentation>false</generateApiDocumentation>
                     <generateApiTests>false</generateApiTests>
                     <generateModels>true</generateModels>
                     <generateModelDocumentation>false</generateModelDocumentation>
                     <generateModelTests>false</generateModelTests>
                     <skipValidateSpec>true</skipValidateSpec>
                     <generateSupportingFiles>true</generateSupportingFiles>
                     <output>${project.build.directory}/generated-sources</output>
                     <!-- Model java package name -->
                     <modelPackage>${default.package}.client.vitality.core.model</modelPackage>
                     <!-- API java package name -->
                     <apiPackage>${default.package}.client.vitality.core.api</apiPackage>

                     <!-- Template to override some default generation template for the webclient -->
                     <templateDirectory>
                        ${project.basedir}/src/main/resources/generator-template-overrides/webclient
                     </templateDirectory>
                     <configOptions>
                        <oas3>true</oas3>
                        <useSpringController>true</useSpringController>
                        <useSpringfox>false</useSpringfox>

                        <sourceFolder>main/java</sourceFolder>
                        <!-- Use modern java8 date/time api -->
                        <dateLibrary>java8</dateLibrary>
                        <java8>true</java8>
                        <!-- Do not include any validation with the client -->
                        <useBeanValidation>false</useBeanValidation>
                        <performBeanValidation>false</performBeanValidation>
                        <interfaceOnly>false</interfaceOnly>
                        <useOptional>false</useOptional>
                        <!-- Make the models serializable -->
                        <serializableModel>true</serializableModel>
                     </configOptions>
                  </configuration>
               </execution>
            </executions>
         </plugin>
      </plugins>
   </build>
</profile>
```

## Getting Started

Clone the Git repository.

```bash
git clone https://github.com/PaloITThailand/openapi-codegen-examples.git
```

Import the maven project inside the `spring-boot-openapi-codegen` using your favorite IDE.

### Generate client

Navigate to the project using a terminal and execute the following command to generate the client code.

```bash
cd spring-boot-openapi-codegen
./mvnw clean generate-sources -P openapi-client-webclient
```

The generated code can be found in the `target` directory.

```
└── target
    └── generated-sources
        ├── api
        └── main
            └── java
                └── com
                    └── paloit
                        └── client
                            └── petstore
                                └── webclient
                                    ├── api                       1. Generated APIs         - api
                                    ├── auth                      2. Shared support files   - auth
                                    ├── model                     3. Generated models       - model
                                    ├── ApiClient.java            4. Shared support files   - apiclient
                                    ├── JavaTimeFormatter.java    4. Shared support files   - apiclient
                                    ├── RFC3339DateFormat.java    4. Shared support files   - apiclient
                                    ├── ServerConfiguration.java  4. Shared support files   - apiclient
                                    ├── ServerVariable.java       4. Shared support files   - apiclient
                                    └── StringUtil.java           4. Shared support files   - apiclient
```

1. Contains the generated APIs
2. Shared classes related to authorization
3. Generated models
4. Re-usable shared support classes

After the generation is complete, the API and model packages (1. and 3.) are copied to `com.paloit.clientpetstore.webclient`.
The support and authorization classes are copied as well and contained in the `apiclient` and `auth` packages.

#### Client initialization & usage

The client has to be defined as a spring bean. This is done within the `com.paloit.config.ApiClientConfig` class. A simple example
is depicted below and should be self-explanatory.

```java
/**
 * ApiClientConfig and webclient is re-used for different APIs
 */
@Configuration
public class ApiClientConfig {
   /**
    * The Webclient instance is shared between the generated clients.
    * 
    * @param webclient The re-usable webclient defined in the WebclientConfig class
    * @param petstoreBasePath Base path of the client is defined in the application.yaml
    */
   public ApiClientConfig(
           WebClient webclient,
           @Value("${client.petstore.base-path}") String petstoreBasePath
   ) {
      this.webclient = webclient;
      this.petstoreBasePath = petstoreBasePath;
   }

   /**
    * Client is defined as a spring bean and can be autowired in other Spring Beans. Note that
    * the specific API client is using the WebClient instance autowired with the constructor. The base path
    * is taken and autowired with @Value from the application.yml.
    * 
    * @return Generated API client
    */
   @Bean
   public PetApi petClient() {
      return new PetApi(
              new ApiClient(webclient).setBasePath(petstoreBasePath)
      );
   }
}
```

After defining the `PetApi` as a Spring Bean, it can be autowired and used to call the external API as illustrated below.

```java
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PetApiClientTest {

   private PetApi petApiClient;

   @Autowired
   public PetApiClientTest(PetApi petApiClient) {
      this.petApiClient = petApiClient;
   }

   /**
    * Simple Webclient call.
    */
   @Test
   public void getPetById_ValidId_200_ReturnPet_simple() {
      var petId = 1l;
      Pet pet = petApiClient.getPetById(petId).block();

      assertThat(pet.getName()).isNotEmpty();
      assertThat(pet.getId()).isEqualTo(1l);
   }
}
```

#### Custom generator templates

The OpenAPI Generator uses [mustache templates](https://openapi-generator.tech/docs/templating/) to generate client implementations or server stubs. For the majority of cases,
these are sufficient to be used out-of-the-box. For custom use cases, they can be adjusted to generate the desired code.

The templates for all programming languages supported by the generator can be found in their [git repository](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator/src/main/resources/).

The templates can be overridden with the OpenAPI Generator maven configuration using the `templateDirectory` tag. If a template with the same file name as the original
is found in the specified directory, it will override the default template.

```xml
   <templateDirectory>
       ${project.basedir}/src/main/resources/generator-template-overrides/webclient
   </templateDirectory>
```

The overridden templates for the WebClient were copied to the local `/src/main/resources/generator-template-overrides/webclient` directory from
the [openapi-generator/src/main/resources/Java/libraries/webclient](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator/src/main/resources/Java/libraries/webclient) folder in the OpenAPI Generator Git repository.

In this example, the generator templates are used to change a modifier with the generated client to access the ResponseSpec and enable us to have fine-grained
handling of http errors. By default, the PetApi client method `addPet` only returns a Mono as seen below and the `addPetRequestCreation` method is private.

```java
public class PetApi {

   private ResponseSpec addPetRequestCreation(Pet pet) throws WebClientResponseException {
       // implementation...
   }

   public Mono<Pet> addPet(Pet pet) throws WebClientResponseException {
       // implementation...
   }
}
```

The `api.mustache` template is changed at line 72 so that the `addPetRequestCreation` method becomes public and accessible. The relevant
change is depicted below in bold.

<pre>
<b>public</b> ResponseSpec {{operationId}}RequestCreation
</pre>

### Generate server

The server is generated in a similar fashion and illustrates the [API first development approach](https://swagger.io/resources/articles/adopting-an-api-first-approach/). The openapi spec is created
first and a server stub generated from it. The development team then adds implementations for the API.

In order to be able to re-generate the server at any time without affecting the implementations, the delegate pattern is utilized and
wil be further explained below.

Navigate to the project root using a terminal and execute the following command to generate the server code.

```bash
./mvnw clean generate-sources -P openapi-server
```

The generated code can again be found under the target folder.

```
└── target
    └── generated-sources
        └── main
            └── java
                └── com
                    └── paloit
                        └── server
                            └── petstore
                                ├── api                           1. Generated APIs using delegate pattern
                                │   ├── ApiUtil.java
                                │   ├── PetApi.java
                                │   ├── PetApiController.java
                                │   ├── PetApiDelegate.java
                                │   ├── StoreApi.java
                                │   ├── StoreApiController.java
                                │   ├── StoreApiDelegate.java
                                │   ├── UserApi.java
                                │   ├── UserApiController.java
                                │   └── UserApiDelegate.java
                                └── model                         2. Generated models
                                    ├── Address.java
                                    ├── Category.java
                                    ├── Customer.java
                                    ├── ModelApiResponse.java
                                    ├── Order.java
                                    ├── Pet.java
                                    ├── Tag.java
                                    └── User.java
```

After completing the generation, the api and model packages are copied to the `server.petstore` package. The implementations will be
kept in the `controller` package and have to implement the delegate interfaces. The delegate interfaces define default implementations for
all APIs and return a HTTP 501 (Not Implemented) if not overridden.

The `PetApiController` uses either the default `PetApiDelegate` if we do not define our own. However, if we define our own with custom implementations as depicted below,
it will be automatically used.

Note that our delegate implementation has to be a spring bean and the `@Component` annotation is added on top of the class.

```java
@Component
public class PetApiControllerImpl implements PetApiDelegate {

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        if(petId == 1l) {
            var pet = new Pet();
            pet.setId(1l);
            pet.setName("Bear");
            pet.setStatus(StatusEnum.AVAILABLE);
            return ResponseEntity.ok(pet);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Pet> addPet(Pet pet) {
        // TODO: Add implementation...
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    // TODO: Override methods from delegate and implement
}
```
The delegate pattern effectively separates the definition of the API from its implementation and both can be modified independently. The default delegate, api and models
can be re-generated without affecting the implementation and vice-versa.

### Run application

Use below command to start the application. A swagger UI web interface can be accessed under [http://localhost:8090/openapi](http://localhost:8090/openapi) to
inspect and interact with the REST API.

```bash
./mvnw spring-boot:run
```

### Run tests

```bash
./mvnw clean verify
```

## Conclusion

In this article, we have demonstrated how to speed up development with Spring Boot using client- and server code generation. Using these techniques has helped us at PALO IT Thailand to rapidly 
develop our own REST APIs and integrate external ones with our applications. By openly sharing our approach, we hope that it will benefit you, our dear readers, and spark some interest in our other practices that
we will be writing about in the near future.


### We Are Hiring

[PALO IT Thailand](https://www.palo-it.com/th/) is always looking for passionate developers to join our teams.
Are you interested in learning, coding, and applying cutting edges technologies to empower our customers and make the world a better place?

Don't think twice and apply [here](https://www.palo-it.com/th/career)!
