CREATE TABLE `produto` (
  `referencia` varchar(20) DEFAULT NULL,
  `codigobarra` varchar(45) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  UNIQUE KEY `referencia_UNIQUE` (`referencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

