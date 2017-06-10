Spring Boot hibernate tutorial

Usage
===

./gradlew build

./gradlew bootRun

Get all:

`curl http://localhost:8080/pet/`

Get single:

`curl http://localhost:8080/pet/id/1`

Create:

`url -v  --header 'content-type: application/json' -X POST 'http://localhost:8080/pet/' -d '{"name":"name","type":"type"}'`

// TODO: Delete:

// TODO: Put:


Embedded database
====
* You may interact with the embedded database at http://localhost:8080/db
* Use the jdbc name: jdbc:h2:mem:testdb
