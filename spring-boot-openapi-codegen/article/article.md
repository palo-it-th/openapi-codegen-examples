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

Thus an ecosystem of editors and code generation tools were created around the Openapi specification of which the latest version is 3.0.2 at the time of writing.
These tools allow us to create API documentation and generate both clients and servers for a vast multitude of programming languages and frameworks.
This article will focus on utilizing code generation with the popular Spring Boot Java framework which is used in a few of our projects.

## Definitions

### Openapi specification

The following is a short explanation of what Openapi specifications are. In short, they enable us to easily share definitions of a REST API.

More information on openapi specs can be found following the link below.

> The [OpenAPI Specification](https://swagger.io/specification/) (OAS) defines a standard, language-agnostic interface to RESTful APIs which allows
> both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection.
> When properly defined, a consumer can understand and interact with the remote service with a minimal amount of implementation logic.


## Prerequisites

Before getting started, we need to ensure all necessary tools to run the application are available.

- [JDK 11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
- [Cloned project repository](https://github.com/PaloITThailand/openapi-codegen-examples)




## We Are Hiring

[Palo IT (Thailand)](https://www.palo-it.com/th/) is always looking for passionate developers to join our teams.
Interested in learning, coding, and applying cutting edges technologies to empower our customers and make the world a better place?
Apply at [https://www.palo-it.com/th/career](https://www.palo-it.com/th/career)!
