# Web Banking Application

## Overview

The Web Banking Application is a RESTful API designed to manage banking operations such as creating accounts, managing clients, and performing various banking transactions. 
The API is developed using Spring Boot and follows a layered architecture with clear separation between controllers, services, and repositories.

## Table of Contents

- [Installation](#installation)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [DTOs](#dtos)
- [Services](#services)
- [Repositories](#repositories)
- [Exceptions](#exceptions)
- [Testing](#testing)
- [Contributing](#contributing)



## Technologies Used:
- Java
- Spring Boot
- Spring Data JPA
- MapStruct
- JUnit
- Docker
- MySQL
- Hibernate
- Jackson
- Maven



## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/IvanHomziak/webbankingapp.git
   cd web-banking-app

2. **Build the project:**
Ensure you have Maven installed.
- mvn clean install

3. **Run the application:**
- mvn spring-boot:run

4. **Access the API:**
The API will be available at http://localhost:8080/api.

## Project Structure
com.ihomziak.clientmanagerservice.controller: Contains REST controllers for handling client and account-related requests.
com.ihomziak.clientmanagerservice.service: Business logic for managing clients and accounts.
com.ihomziak.clientmanagerservice.dao: Interfaces for database operations, using Spring Data JPA.
com.ihomziak.clientmanagerservice.dto: Data Transfer Objects (DTOs) for transferring data between layers.
com.ihomziak.clientmanagerservice.entity: JPA entities representing the database tables.
com.ihomziak.clientmanagerservice.enums: Enumerations used across the application.
com.ihomziak.clientmanagerservice.exception: Custom exceptions for handling errors.
com.ihomziak.clientmanagerservice.utils: Utility classes for various functions.

## API Endpoints
**Account Controller**

Get Account by UUID

**GET** /api/account/{uuid}
Returns account details for the given UUID.
Get All Client Accounts

**GET** /api/account/list/{uuid}
Returns a list of all accounts associated with a client UUID.
Create Checking Account

**POST** /api/account
Creates a new checking account.
Delete Account

**DELETE** /api/account/{uuid}
Deletes an account by UUID.
Update Account

**PATCH** /api/account
Updates an existing account's details.
Get All Accounts

**GET** /api/account
Returns a list of all accounts in the system.
Client Controller
Add Client



**Client Controller**

**POST** /api/clients
Adds a new client.
Get Client by UUID

**GET** /api/clients/{uuid}
Returns client details for the given UUID.
Get All Clients

**GET** /api/clients
Returns a list of all clients.
Delete Client

**DELETE** /api/clients/{uuid}
Deletes a client by UUID.
Update Client

**PATCH** /api/clients/update
Updates an existing client's details.

## DTOs

AccountInfoDTO: Represents basic account information.
AccountRequestDTO: Represents a request to create or update an account.
AccountResponseDTO: Represents detailed account information, including account holder details.
ClientRequestDTO: Represents a request to create or update a client.
ClientResponseDTO: Represents detailed client information.
ClientsInfoDTO: Represents basic client information.
ErrorDTO: Represents error details for error handling.

## Services

AccountService: Interface for account-related operations.
ClientService: Interface for client-related operations.

## Repositories

AccountRepository: Handles data operations related to accounts.
ClientRepository: Handles data operations related to clients.

## Exceptions

AccountNotFoundException: Thrown when an account is not found.
ClientNotFoundException: Thrown when a client is not found.
ClientAlreadyExistException: Thrown when attempting to create a client that already exists.
AccountNumberQuantityException: Thrown when the maximum number of accounts of a certain type is exceeded.

## Testing

The application includes unit and integration tests to ensure the correctness of the business logic and API endpoints.





