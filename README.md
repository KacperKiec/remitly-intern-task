# Remitly Intern Task - Spring Boot Application

This repository contains a Spring Boot application developed for the Remitly Intern Task. The application provides operations to add and retrieve bank details via SWIFT codes and integrates with a PostgreSQL database.

## Table of Contents
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)

## Technologies

This project uses the following technologies:
- Java 21 (or higher)
- Spring Boot 3.x
- PostgreSQL
- Maven
- Docker (for containerization)
- Docker Compose (for multi-container orchestration)

## Prerequisites

Before you begin, ensure that you have the following installed:
- **Docker** (installed and running)
- **Docker Compose** (for orchestration of multi-container setups)

If you don't have Docker and Docker Compose installed, follow the installation guide on the official Docker website: https://docs.docker.com/get-docker/

## Setup Instructions

Follow these steps to set up the application using Docker Compose.

### Step 1: Clone the Repository

First, clone the repository to your local machine and enter the catalog:

git clone https://github.com/KacperKiec/remitly-intern-task.git
cd remitly-intern-task

### Step 2: Docker Compose Setup

The docker-compose.yml file is already configured to run both the Spring Boot application and the PostgreSQL database in Docker containers. It will automatically set up the necessary environment for both.

### Step 3: Build and Run the Application with Docker Compose

Run the following command in the root directory of the project to build the containers and start the application:

docker-compose up --build

The application will be available at http://localhost:8080.
