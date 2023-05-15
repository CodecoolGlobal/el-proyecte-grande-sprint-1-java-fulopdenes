# TaskTiger 🐯
### A school project by our hard-working team, _SOLID_.

### SOLID
* Siyar FAROUQ ([@siyar25](https://github.com/siyar25))
* Zoltán MIHÁLYFI ([@miz092](https://github.com/miz092))
* Dénes FÜLÖP ([@fulopdenes](https://github.com/fulopdenes))

## About the Project

This was our last team project in [Codecool](https://codecool.com/)'s 10-month Full-Stack developer course. It was a 4-sprint long project.<br>
**TaskTiger** is a platform where clients can connect with 'Taskers' to complete different jobs/tasks for them, creating easier access to different service providers. All this within a few comfortable clicks/taps.<br>
The project includes authentication and authorization, profile pages, booking reservations, messaging, reviewing, along with many other features.

**This repository is only the backend of the project!** You can find the frontend repository [here](https://github.com/miz092/TaskTigerFrontend).

_This README is only about the backend part of the project. For the frontend documentation, refer to the frontend repository's README._

### Used Technologies

* Java 17
* Spring Boot
* Spring Security with JSON Web Tokens
* Spring Data JPA
* PostgreSQL

## Getting Started

You'll need the Maven build tool and Java 17 to build the application. For the database a new PostgreSQL database will be needed called `tasktiger`. Username and password for the database need to be passed to the application as _environment variables_.

Now the application can be started. The database will be filled with some initial data. Spring Boot will launch the server, and the endpoints will be live.
