# rock-paper-scissors.app

## Requirements

For building and running the application you need:

- [JDK 1.8 or higher](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3 or higher](https://maven.apache.org)

# How to run it locally
1. [Download](https://codeload.github.com/ajardines/rock-paper-scissors-api/zip/refs/heads/master) or clone the [repository](https://github.com/ajardines/rock-paper-scissors-api) to your local machine:
```bash
$ git clone https://github.com/ajardines/rock-paper-scissors-api.git
```

2. Run `mvn install` inside the downloaded/cloned folder:
```bash
$ mvn install
```

3. Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
