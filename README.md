Getting started
---------------
documentation on how to setup and run the project

Prerequisites

- Java 8 or higher version
    Make sure you have installed java 8 or higher version in your machine and set the JAVA_HOME environment variable.
    If not installed, download and install from https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Maven 3.3.9
    Make sure you have installed maven 3.3.9 or higher version in your machine and set the M2_HOME environment variable.
    If not installed, download and install from https://maven.apache.org/download.cgi
- Git
    Make sure you have installed git in your machine. If not installed, download and install from https://git-scm.com/downloads
- IDE
    Make sure you have installed any IDE in your machine. If not installed, download and install from https://www.jetbrains.com/idea/download/#section=windows


Usage
-----
Clone the repository and run the following commands:

```bash
$ git clone git@github.com:makwasi1/compulynx-api.git
$ cd compulynx-api
$ mvn clean install
$ mvn spring-boot:run
check the application on http://localhost:8080
```

Access the application
----------------------
documentation on how to access the project

```bash
open the browser and type the following url  (chrome, firefox, safari, internet explorer)
$ http://localhost:8092
```

Application Endpoints
-----------------------
documentation on how to run the project apis swagger documentation

```bash
$ http://localhost:8092/swagger-ui/index.html#/
```


Testing the application
-----------------------
documentation on how to use the project

```bash
$ mvn test
```

Shutdown the application
------------------------
documentation on how to stop the project

```bash
$ mvn spring-boot:stop
```


