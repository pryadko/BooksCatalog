-- Set proper character set
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

CREATE DATABASE IF NOT EXISTS `BooksCatalog` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `BooksCatalog`;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `AuthorToBook`;
DROP TABLE IF EXISTS `Author`; 
DROP TABLE IF EXISTS `Book`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`; 

CREATE TABLE `Author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `Book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `shortDescription` varchar(300) NOT NULL,
  `year` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `users` (
  `USER_ID` int unsigned NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` int unsigned NOT NULL,
  `USER_ID` int unsigned NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `AuthorToBook` (
  `authorId` int NOT NULL,
  `bookId` int NOT NULL,
  PRIMARY KEY (`authorId`,`bookId`),
  KEY `book_fk` (`bookId`),
  CONSTRAINT `author_fk` FOREIGN KEY (`authorId`) REFERENCES `Author` (`id`),
  CONSTRAINT `book_fk` FOREIGN KEY (`bookId`) REFERENCES `Book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS=1;

-- Insert data
INSERT INTO `Author` (`id`, `firstname`, `lastname`) VALUES
(1, 'Майн', 'Рид'),
(2, 'Брюс', 'Эккель'),
(3, 'Herbert', 'Schildt'),
(4, 'Bert', 'Bates'),
(5, 'Kathy', 'Sierra'),
(6, 'Joshua', 'Bloch'),
(8, 'Кей', 'Хорстманн'),
(9, 'Гари', 'Корнелл');

INSERT INTO `Book` (`id`, `name`, `shortDescription`, `year`) VALUES
(1, 'Всадник без головы', 'Американский приключенческий роман', 1962),
(2, 'Философия Java', 'Учебное пособие для изучающих язык програмирования Java', 2010),
(3, 'Java 2. Библиотека профессионала. Том 2. Тонкости программирования', 'Это второй том книги, которую можно назвать учебником от создателя(Sun Press).', 2010),
(4, 'Head First Java, 2nd Edition', 'Learning a complex new language is no easy task especially when it s an object-oriented computer programming language like Java.', 2005),
(5, 'Effective Java (2nd Edition)', 'Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable?', 2008);

INSERT INTO `AuthorToBook` (`authorId`, `bookId`) VALUES
(1, 1),
(2, 1),
(2, 2),
(4, 4),
(5, 4),
(6, 5),
(8, 3),
(9, 3);

INSERT INTO `users` (`USER_ID`, `USERNAME`, `PASSWORD`, `ENABLED`) VALUES
(100, 'user', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 1),
(200, 'admin', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 1);

INSERT INTO `user_roles` (`USER_ROLE_ID`, `USER_ID`, `AUTHORITY`) VALUES
(1, 100, 'ROLE_USER'),
(200, 200, 'ROLE_ADMIN');