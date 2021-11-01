# Watchers
[![Build Status](https://travis-ci.org/adavid9/watchers.svg?branch=master)](https://travis-ci.org/adavid9/watchers)

### It was made using following technologies:

- _Spring Boot_
- _Spring Security_
- _JSP_
- _Spring Data JPA_
- _Spring Data REST_
- _H2 (development database)_
- MySQL (production database)

### Most important functionalities
- Register & Logout
- Administrator
  - Add/Remove/Update Series
  - Add/Remove/Update Season
  - Add/Remove/Update Episodes
  - Add/Remove/Update Movies
  - Delete the account
  - Delete the account of different user
  - Change the password
  - Change the password of different user
  - View all accounts
- User
  - Add Movies from the available movies
  - Add Series from the available series
  - Add Seasons from the available seasons
  - Add Episodes from the available episodes
  - Delete the account
  - Change the password

#### Configuration files

Folder <b>src/resources</b> contains config files for this Spring application.

- <b>src/resources/application.properties</b> - main configuration file. Here it's possible to change the port number.
- <b>src/resources/application-dev.properties</b> - configuration file for development. Contains configuration for development.
- <b>src/resources/application-prod.properties</b> - configuration file for production. Contains configuration for production.
### How to run

There are several ways to run the application. You can run it from the command line with included Maven Wrapper or Maven
itself.

Once the application starts, go to the web browser and visit <code>http://localhost:48080
</code>

### Maven Wrapper

#### Using the Maven Plugin

Go to the root directory of the application and type:

```
$ chmod +x mvnw
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Using Executable Jar

Or you can build the JAR file with

```
$ ./mvnw clean package 
```

Then you can run the JAR file

```
$ java -jar -Dspring.profiles.active=dev target/watchers-1.0.war
```

### Maven

Open a terminal and run the following commands to ensure that you have valid versions of Java and Maven installed

```
$ java -version
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_292-b10)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.292-b10, mixed mode)
```

```
$ mvn -version
Maven home: /Users/your_username/.mvnvm/apache-maven-3.6.3
Java version: 1.8.0_292, vendor: AdoptOpenJDK, runtime: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre
Default locale: en_PL, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
```

#### Using the Maven Plugin

The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application.
Applications run in an exploded form, as they do in your IDE. The following example shows a typical Maven command to run
a Spring Boot application:

```
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Using Executable Jar

Or you can build the JAR file with

```
$ mvn clean package
```

Then you can run the JAR file

```
$ java -jar -Dspring.profiles.active=dev target/watchers-1.0.war
```

### Tests
Tests can be run by executing the following command from the root directory of the application
```
$ mvn test
```