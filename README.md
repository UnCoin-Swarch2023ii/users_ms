User_ms
=======

Description
-----------
This is a microservice for UnCoin. It is responsible user management. Including:

User login
User Register

This is a REST API using  the following technologies:.

* java
* maven
* postgresSQL
* SpringBoot
* docker

## Project Structure
```
├───.idea
│   ├───dataSources
│   └───httpRequests
├───.mvn
│   └───wrapper
└───src
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───example
    │   │           └───Users
    │   │               ├───controller
    │   │               ├───entity
    │   │               ├───exceptions
    │   │               ├───repository
    │   │               ├───response
    │   │               ├───security
    │   │               │   └───config
    │   │               └───service
    │   └───resources
    │       ├───db
    │       │   └───migration
    │       ├───static
    │       └───templates
    └───test
        └───java
            └───com
                └───example
                    └───Users
```
## Endpoints
POST http://localhost:8080/api/v1/user/signup

DELETE http://localhost:8080/api/v1/user/delete/{{document}}

GET http://localhost:8080/api/v1/user/get/{{document}}

POST http://localhost:8080/api/v1/user/signin
Content-Type: application/json
```
{
  "user_name" : "Juli",
  "document" : 43434343,
  "password" : "string"
}
```
PUT http://localhost:8080/api/v1/user/update/{{document}}

POST http://localhost:8080/api/v1/user/signup
Content-Type: application/json
```
{
  "user_name" : "Juli",
  "user_lastname" : "Cardozo",
  "document" : 43434343,
  "balance" : 0,
  "password" : "string",
  "enable" : true
}
```

POST http://localhost:8080/api/v1/company/signup
Content-Type: application/json
```
{
  "company_name" : "Empresa_Juli2",
  "document" : 102,
  "balance" : 0,
  "password" : "string"
}
```
POST http://localhost:8080/api/v1/company/signin
Content-Type: application/json
```
{
  "company_name" : "Empresa_Juli2",
  "document" : 102,
  "password" : "string"
}
```
GET http://localhost:8080/api/v1/company/get/10
