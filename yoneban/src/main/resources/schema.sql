DROP TABLE IF EXISTS `PersistentMatch`;

CREATE TABLE `PersistentMatch` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `matchID` varchar(255) NOT NULL,
  `gotBanned` bit(1) NOT NULL,
  `counter` int NOT NULL,
  PRIMARY KEY (`id`)
);