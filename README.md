Spring Boot properties tutorial

Usage
===

./gradlew build

./gradlew HelloService:springBootJar

Default configuration profile
====

java -jar ./HelloService/build/libs/helloworld-0.0.1-SNAPSHOT.jar

Production configuration profile
====

java -Dspring.profiles.active=production -jar ./HelloService/build/libs/helloworld-0.0.1-SNAPSHOT.jar

