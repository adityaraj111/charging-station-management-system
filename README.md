# Charging Authentication Service

This is a Spring Boot application for handling charging authentication requests using Kafka.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Docker (for running Kafka)

## Getting Started

### Clone the repository

```bash
git clone https://github.com/adityaraj111/charging-authentication-service.git
cd charging-authentication-service
```


# Charging Transaction Service

This project is a Spring Boot application for handling charging transactions. It uses Kafka for messaging and is written in Kotlin.

## Prerequisites

- JDK 17 or higher
- Apache Maven 3.8.1 or higher
- Docker (for running Kafka)
- IntelliJ IDEA (recommended for development)


### Clone the Repository

```sh
git clone https://github.com/adityaraj111/charging-transaction-service.git
cd charging-transaction-service
```

API Endpoints
The application exposes the following API endpoint:  
POST /transaction/authorize: Authorize a transaction.

```bash

curl --location 'http://localhost:8080/transaction/authorize' \
--header 'Content-Type: application/json' \
--data '{
    "stationUuid": "abcdef",
    "driverIdentifier": {
        "id": "cms9999489047893090989826781"
    }
}'
```

## Start Kafka and Zookeeper:

docker-compose up -d

