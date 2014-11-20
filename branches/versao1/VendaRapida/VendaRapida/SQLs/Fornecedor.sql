--
--Fornecedor: 
-- 
-- ID 
-- Nome
-- CNPJ/CPF
 
-- Endereco
-- Bairro
-- Cidade
-- Estado

-- Contato
-- Email
-- Telefone

-- Produtos
-- IDProduto -> referencia tabela produtos
-- Descricao
-- Unidade
-- ValorProdutoFornecedor


DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE  TABLE `vendarapida`.`fornecedor` (
  `id` INT NOT NULL ,
  `nome` VARCHAR(60) NULL ,
  `CNPJCPF` VARCHAR(14) NULL ,
  `Endereco` VARCHAR(150) NULL ,
  `Email` VARCHAR(60) NULL ,
  `Telefone` VARCHAR(12) NULL ,
  PRIMARY KEY (`id`) );








