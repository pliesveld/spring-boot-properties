Spring Boot hibernate tutorial

Usage
===

./gradlew build

./gradlew bootRun

Get all:

`
curl -s -X GET http://localhost:8080/pet/ | json_pp
`

Get single:

`
curl -s http://localhost:8080/pet/id/1 | json_pp
`

Create:

`
curl -s --header 'content-type: application/json' -X POST 'http://localhost:8080/pet/' -d '{"name":"name","type":"type"}' | json_pp
`

Delete:

`
curl -s -v -X DELETE 'http://localhost:8080/id/2'  | json_pp
`

PUT:
`
curl -s --header 'content-type: application/json' -X PUT 'http://localhost:8080/pet/id/1' -d '{"owner":"Patrick"}'
curl -s --header 'content-type: application/json' -X PUT 'http://localhost:8080/pet/id/1' -d '{"name":"Odie","type":"Dog","owner":"Patrick"}'
`


Embedded database
====
* You may interact with the embedded database at http://localhost:8080/db
* Use the jdbc name: jdbc:h2:mem:testdb
