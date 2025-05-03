# Books Catalog

A web application for managing books and authors, built with Spring MVC and Hibernate.

## Technologies

- Java 21
- Spring Framework 6.2.6
- Spring Security
- Hibernate 6.4.3
- MySQL 8
- JSP/JSTL
- Maven

## Features

- Book and author management
- User authentication and authorization (Admin/User roles)
- Internationalization support (English/Russian)
- CRUD operations for books and authors
- Search functionality
- Many-to-many relationship between books and authors

## Prerequisites

- JDK 21
- Maven 3.x
- Docker and Docker Compose
- OR MySQL 8.x (if running database locally without Docker)

## Setup

### Option 1: Using Docker (Recommended)

1. Start the MySQL database:
```bash
docker compose up -d
```

2. Build the project:
```bash
mvn clean install
```

3. Deploy the WAR file to a Jakarta EE 10 compatible server (e.g., WildFly)

### Option 2: Local MySQL Installation

1. Create MySQL database and user:
```sql
CREATE DATABASE BooksCatalog;
CREATE USER 'BooksCatalog'@'localhost';
GRANT ALL PRIVILEGES ON BooksCatalog.* TO 'BooksCatalog'@'localhost';
```

2. Follow steps 2-3 from Option 1

### Database Configuration

When using Docker, the following credentials are configured:
- Database: BooksCatalog
- Username: BooksCatalog
- Password: bookscatalog
- Host: localhost
- Port: 3306

## Default Users

- Admin: username=admin, password=123
- User: username=user, password=123

## Security

- Regular users can view books and authors
- Admins can create, edit, and delete books and authors
- All data transmissions are secured with TLS
- Session cookies are HTTP-only and secure

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

