# kotlin-spring-data-mongodb-reactive
kotlin-spring-data-mongodb using spring boot + webflux

### Requirements ###

* Spring Boot 3.0.1 + webflux
* Kotlin 1.8.0
* Kotlin Coroutines
* Kotest 5.5.5
* Java 18 (feat.corretto-18)
* MongoDB 4.4.6
* Gradle 7.4.2
* Docker

### Building the artifact ###

```
gradle build
```

### Running the application from command line ###

```
gradle bootRun
````

### MongoDB container environment  ###

#### docker-compose(require) ####

- docker-compose up -d
- docker ps check
- mongodb 환경 구성
    - mongo1, mongo2, mongo3 check
    - replica set 생성(rs.initiate())
    - rs.status() check
    - testdb 생성
    - testdb에 order collection 생성
    - resources/dummy/order-json 데이터 insert

### Test Url ###

```
kotlin/pe/kotlin/mongo//findOrder.http
```
