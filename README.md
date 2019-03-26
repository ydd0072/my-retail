# myRetail Products Service

## Restful API

Welcome to myRetail's latest technology achievement, a Restful API giving you and your colleagues an easy access to our product data.

## Architecture
The API is built with [Spring Boot](https://spring.io/projects/spring-boot), a high performing framework to deliver production grade applications.
The product pricing data is stored in [Redis](https://redis.io/), a super-fast and a rock-solid NoSQL datastore to deliver data in O(1) time. 
Product service uses a [Caffeine in-memory cache](https://github.com/ben-manes/caffeine) to save time and network bandwidth when repeated data is fetched over network for the same products.

## Endpoints

GET /products/{id}

Get the product information (including current price) for a given product.

PUT /products/{id}

Update the price for the given product.

For full details on the request / response parameters as well as a live api, try out the instance below.

## Available online

You can try out an example GET request with https://morning-citadel-49305.herokuapp.com/products/id/13860429

## Application Health Check & Metrics
You can view application parameter live at https://morning-citadel-49305.herokuapp.com/actuator/

## Running locally

To run the API locally, you'll first need to install Redis. Please follow https://redis.io/download.
If you're on a Mac, you can easily install Redis with brew by running `brew install redis`. 
Or if you're running on Windows, you can install Redis with the Microsoft port available at https://github.com/MicrosoftArchive/redis/releases

Mac users only - Start up Redis using command `redis-server` on the default port of 6379 and redis will be available to read/write.
Please use any Redis UI for key/value data validations. I prefer [Medis](https://github.com/luin/medis).  

If you want to run on a different port or use a Redis instance running elsewhere besides `localhost`, you can customize this by setting the value in the `spring.redis.url` variable in application.properties file.

Then run the Gradle `bootRun` task to generate the Swagger UI and start up the server. 
Use the included Gradle wrapper `gradlew` to automatically download the version of Gradle included with this project.

1. `./gradlew bootRun`
2. Swagger available at http://localhost:8080/swagger-ui.html

## Test Cases

The code ships with API functionality tests covered with Postman integration tests as well as some unit tests which covers variety of scenarios on both endpoints. 
The test cases are located in the `src/test/` folder.
Please run the Gradle `bootRun` command to run the API and run local Redis (or connect to remote Redis) and import `my-retail-api.postman_collection.json` in [Postman](https://www.getpostman.com/downloads/) and execute all of the integration tests.

## Future Improvements

1) Security for PUT needs to added.
2) Global exception handling and some missing validations e.g. currency.
3) Redis Sentinel for real prod and security around it.



