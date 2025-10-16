INSERT INTO role (nome_role) VALUES ('ROLE_CADASTROS');
INSERT INTO role (nome_role) VALUES ('ROLE_FATURAMENTO');
INSERT INTO role (nome_role) VALUES ('ROLE_ADMIN');

INSERT INTO usuario (login, nome, email, senha) VALUES ('maria', 'Maria', 'maria@teste.com.br','$2a$10$udxISip/UySBN9dCBT0fgOjOvEpS1PyNMM2mJcWlBrmPWexzIiIrW');
INSERT INTO usuario (login, nome, email, senha) VALUES ('joao', 'João', 'joao@teste.com.br','$2a$10$udxISip/UySBN9dCBT0fgOjOvEpS1PyNMM2mJcWlBrmPWexzIiIrW');
INSERT INTO usuario (login, nome, email, senha) VALUES ('jose', 'José', 'jose@teste.com.br','$2a$10$9MBN/aN0ehDYm42wLpdG9.svMJ1ItL3CQB7GejFXloxeD6rxSBoWe');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('maria','ROLE_CADASTROS');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('joao','ROLE_FATURAMENTO');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('jose','ROLE_ADMIN');

INSERT INTO categorias (nome, situacao) VALUES ('Eletrônicos','Ativo');
INSERT INTO categorias (nome, situacao) VALUES ('Vestuário','Ativo');
INSERT INTO categorias (nome, situacao) VALUES ('Móveis','Desativado');
INSERT INTO categorias (nome, situacao) VALUES ('Acessórios','Ativo');

INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('TV 50"', 'TV Samsung 50 Polegadas', 3500.00, 1, '2022-03-28', 'Ativo');
INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('IPhone 11"', 'Smartphone Apple Iphone 11 5G', 5290.00, 1, '2022-03-27', 'Ativo');
INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('Camisa Social', 'Camisa Social nº 3 Azul', 98.00, 2, '2022-03-26', 'Ativo');
INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('Phone Games', 'Phone Tipo Concha Tipo Gamer', 150.00, 4, '2022-03-28', 'Ativo');
INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('Cadeira Classca"', 'Caderia Madeira Tipo Clássica', 120.00, 3, '2022-03-28', 'Ativo');
INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('Calça Jeans M', 'Calça Jeans Preta Tamanho M', 89.90, 2, '2022-03-28', 'Ativo');
INSERT INTO produtos (nome, descricao, preco, id_categoria, data_cadastro, situacao) VALUES ('Porta Retrato de Mesa', 'Porta Retrato de Mesa 30x20cm', 25.00, 1, '2022-03-28', 'Ativo');
