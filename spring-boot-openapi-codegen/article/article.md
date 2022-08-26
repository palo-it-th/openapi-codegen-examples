# Spring Boot: Client- and server code generation using Openapi 3 specs 

REST APIs are ubiquitous in today's world ranging from payments, communication and banking. Developers can plug-in virtually any specialist service they require for their applications.

So it comes as no suprpise that we at Palo IT are frequently developing APIs for our customers and integrating external APIs with our backend applications.
Both our own APIs and external ones can become rather big and extensive making it quite time consuming to write them all by hand.
Luckily, there are some nifty techniques to make our lives as developers easier and avoid writing repetitive code.

Enter OpenApi 3 specs and code generation!

Imagine someone needs to access our freshly developed API.
In the past we would share our endpoint definitions using a non-standardized way such as Excel sheets.
The consumers of our API would have to implement a client within their application.
With large APIs this can take quite a while. In order to not re-invent the wheel everytime, the OpenApi spec was created.
It enables us to define our REST API contract in a standardized format and easily share it with others.

Thus an ecosystem of editors and code generation tools were created around the Openapi specification of which the latest version is 3.1 at the time of writing.
These tools allow us to create API documentation and generate both clients and servers for a vast multitude of programming languages and frameworks.
This article will focus on utilizing code generation with the popular Spring Boot Java framework which is used in a few of our projects.

## Definitions

### Openapi specification

The following is a short explanation of what Openapi specifications are. In short, they are an open API description format and enable us to easily share definitions of a REST API.


> The [OpenAPI Specification](https://swagger.io/specification/) (OAS) defines a standard, language-agnostic interface to RESTful APIs which allows
> both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection.
> When properly defined, a consumer can understand and interact with the remote service with a minimal amount of implementation logic.

### Client code

We will refer to client code as any client SDK that enables us to call a remote REST API. In the context of spring boot,
client code is usually written using HTTP clients such as [Rest Template](https://docs.spring.io/spring-android/docs/current/reference/html/rest-template.html) or
the more recently introduced [Webclient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client).

### Server code

By Server code we will refer to any code necessary to create a REST API server. Using spring boot, this is usually accomplished
with the servlet api based [Spring Web](https://spring.io/guides/tutorials/rest/) or the newer, non-blocking [Spring Webflux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html).

### Code generation

The code generation we will learn about in this tutorial will be done using the [Openapi Codegenerator](https://github.com/OpenAPITools/openapi-generator).
It is a community project widely used by many well-known companies and can generate client code or server stubs from an openapi spec in a multitude of programming languages and frameworks. There are many ways to use the generator.
It comes as a maven plugin, npm module and standalone JAR. In the following sections we will utilize the maven plugin for convenience purposes since the spring boot project
uses it as the build management tool.

On a side note, the openapi generator was forked from the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen) to simplify the original and enable stronger community ownership. 

## Tutorial Scope

With this tutorial, we will demonstrate how to generate both server-side and client-side code from an openapi v3 specification. The v3 specification
is the latest openapi spec iteration and extends the older v2 version with numerous new [functionalities and improvements](https://blog.stoplight.io/difference-between-open-v2-v3-v31).

For simplicity's sake, the [Swagger Petstore](https://petstore.swagger.io/) will be used for the generation. It is a simple example REST API demonstrating the capabilities of
the openapi specification including authorization, HTTP schemes and API descriptions.

### Server

For the server-side, the generator will output traditional spring web based code.

### Client

To illustrate how different client code can be generated, we will generate clients with both the aforementioned reactive Webclient and RestTemplate. For our projects,
we at Palo IT prefer to use the Webclient because it simplifies performing concurrent calls compared with the Rest Template.

## Prerequisites

Before getting started, we need to ensure all necessary tools to run the application are available. Installation instructions
for different 

- [JDK 11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
- [Cloned Git Repository](https://github.com/PaloITThailand/openapi-codegen-examples)

## Project structure
To become familiar with the components and structure of the project, the main areas of interest are depicted below.

The Petstore openapi specification is kept here.
Both server and client contain the same spec but have been kept in separate folders for simplicity
```
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── paloit
    │   │           ├── client
    │   │           │   └── petstore
    │   │           │       ├── feign ->        (1. Generated feign client)
    │   │           │       │   ├── api
    │   │           │       │   ├── config
    │   │           │       │   └── model
    │   │           │       └── webclient ->    (2. Generated webclient)
    │   │           │           ├── api
    │   │           │           ├── apiclient
    │   │           │           ├── auth
    │   │           │           └── model
    │   │           ├── config ->               (3. General project configuration)
    │   │           └── server
    │   │               └── petstore ->         (4. Generated server)
    │   │                   ├── api
    │   │                   ├── controller
    │   │                   └── model
    │   └── resources
    │       ├── generator-template-overrides -> (5. Generation template adjustment)
    │       │   └── webclient
    │       │       └── auth
    │       └── openapi
    │           ├── client ->                   (6. Petstore Openapi Spec)
    │           └── server ->                   (7. Petstore Openapi Spec)
    └── test
        └── java
            └── com
                └── paloit ->                   (8. Client and Server Tests)
                    ├── client
                    │   └── petstore
                    │       ├── feign
                    │       └── webclient
                    └── server
                        └── petstore
                            └── controller
```

1. The generated feign client code
2. The generated webclient code
3. Common configuration for the webclient, springdoc api documentation, Jackson serial - and deserializer
4. The generated spring web server stubs
5. Adjustment for the generation templates. Sometimes custom adjustments are needed for the generation process if the
   default templates are not sufficient. More about this in later sections.
6. Tests for both generated server and client

The configuration for the openapi generator can be found in the pom file. To separate configuration for the two clients
and server generator, maven profiles have been used as seen below.

### Webclient generator configuration

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

Clone the git repository

```bash
git clone https://github.com/PaloITThailand/openapi-codegen-examples.git
```

Import the maven project inside the `spring-boot-openapi-codegen` using your favorite IDE.

### Generate client

Navigate to the project using a terminal and execute the following command to generate the client code.

```bash
./mvnw 
```

### Generate server


## We Are Hiring

[Palo IT (Thailand)](https://www.palo-it.com/th/) is always looking for passionate developers to join our teams.
Interested in learning, coding, and applying cutting edges technologies to empower our customers and make the world a better place?
Apply at [https://www.palo-it.com/th/career](https://www.palo-it.com/th/career)!
