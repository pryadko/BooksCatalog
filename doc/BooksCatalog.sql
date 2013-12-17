Drop table if exists `AuthorToBook`;
Drop table if exists `Author`; 
Drop table if exists `Book`;

Drop table if exists `user_roles`;
Drop table if exists `users`; 

CREATE TABLE IF NOT EXISTS `Author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;


INSERT INTO `Author` (`id`, `firstname`, `lastname`) VALUES
(1, 'Майн', 'Рид'),
(2, 'Брюс', 'Эккель'),
(3, 'Herbert', 'Schildt'),
(4, 'Bert', 'Bates'),
(5, 'Kathy', 'Sierra'),
(6, 'Joshua', 'Bloch'),
(8, 'Кей', 'Хорстманн'),
(9, 'Гари', 'Корнелл');



CREATE TABLE IF NOT EXISTS `AuthorToBook` (
  `authorId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`authorId`,`bookId`)
 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `AuthorToBook` (`authorId`, `bookId`) VALUES
(1, 1),
(2, 1),
(2, 2),
(4, 4),
(5, 4),
(6, 5),
(8, 3),
(9, 3);


CREATE TABLE IF NOT EXISTS `Book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `shortDescription` varchar(300) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;


INSERT INTO `Book` (`id`, `name`, `shortDescription`, `year`) VALUES
(1, 'Всадник без головы', 'Американский приключенческий роман', 1962),
(2, 'Философия Java', 'Учебное пособие для изучающих язык програмирования Java', 2010),
(3, '«Java 2. Библиотека профессионала. Том 2. Тонкости программирования»', 'Это второй том книги, которую можно назвать учебником от создателя(Sun Press).', 2010),
(4, 'Head First Java, 2nd Edition', 'Learning a complex new language is no easy task especially when it s an object-oriented computer programming language like Java. ', 2005),
(5, 'Effective Java (2nd Edition)', 'Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable?', 2008);


CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` int(10) unsigned NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `users` (`USER_ID`, `USERNAME`, `PASSWORD`, `ENABLED`) VALUES
(100, 'user', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 1),
(200, 'admin', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 1);


CREATE TABLE IF NOT EXISTS `user_roles` (
  `USER_ROLE_ID` int(10) unsigned NOT NULL,
  `USER_ID` int(10) unsigned NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user_roles` (`USER_ROLE_ID`, `USER_ID`, `AUTHORITY`) VALUES
(1, 100, 'ROLE_USER'),
(200, 200, 'ROLE_ADMIN');


ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);
ALTER TABLE `AuthorToBook`
  ADD CONSTRAINT `Constr_AuthorToBook_Author_fk`
        FOREIGN KEY `author_fk` (`authorId`) REFERENCES `Author` (`id`);
ALTER TABLE `AuthorToBook`
  ADD CONSTRAINT `Constr_AuthorToBook_Book_fk`
        FOREIGN KEY `book_fk` (`bookId`) REFERENCES `Book` (`id`);

