

INSERT INTO autor ( foto, lastname_author, name_author) VALUES ( 'e5db0f37-9ec7-4a34-b294-e38f98de2a58_vargas llosa.jpg', 'Vargas Llosa', 'Mario');
INSERT INTO autor ( foto, lastname_author, name_author) VALUES ( '59cbeb70-cd07-4772-9ace-4d630b8b352f_borges.jpg', 'Borges', 'Jorge Luis');
INSERT INTO autor ( foto, lastname_author, name_author) VALUES ( '82aaa265-dd5e-4a7f-80d9-8d64edfd1697_rulfo_juan.jpg', 'Rulfo', 'Juan');
INSERT INTO autor ( foto, lastname_author, name_author) VALUES ( '8d176acb-a4aa-4659-a6f4-17420653ac4c_cesar-vallejo.jpg', 'Vallejo', 'Cesar');
INSERT INTO autor ( foto, lastname_author, name_author) VALUES ( '1557ae47-f82c-4bf5-830c-08bdbb5d3535_ricardopalma.jpg', 'Palma', 'Ricardo');

INSERT INTO book ( date_of_admission_book, edition_book, foto, language_book, name_book, serie_book, id_author) VALUES ( '1878-01-01', 2, 'defe58bf-4745-44f5-80cd-f2bdab26bfef_laciudadylosperros.jpg', 'Español', 'La ciudad y los perros', 2, 1);
INSERT INTO book ( date_of_admission_book, edition_book, foto, language_book, name_book, serie_book, id_author) VALUES ( '1987-01-01', 2, '1566c66c-55f2-483c-809e-2175507b88ac_el-aleph-1.jpg', 'Español', 'El Aleph', 2, 2);
INSERT INTO book ( date_of_admission_book, edition_book, foto, language_book, name_book, serie_book, id_author) VALUES ( '1997-01-01', 2, 'ba754c39-7a7e-4a1d-975d-bfaba7e0a0ad_paramo.jpg', 'Español', 'Pedro paramo', 1, 3);

INSERT INTO role (name_role)	VALUES ('ROLE_ADMIN');
INSERT INTO role (name_role)	VALUES ('ROLE_ASISTENTE');
INSERT INTO role (name_role)	VALUES ('ROLE_ESTUDIANTE');

INSERT INTO account ( correo_account, dni_account, last_name_account, name_account, password_account, id_role)
           VALUES ('admin@gmail.com', 12345678, 'Del Sistema', 'Admin','$2a$10$Ml1yMr8NB9/v99OSnOx2Guz93rGEIhUDVT73etTZhsIMrIw0aJlo2' , 1);
INSERT INTO account ( correo_account, dni_account, last_name_account, name_account, password_account, id_role)
           VALUES ('web@gmail.com', 98765432, 'Del Sistema', 'Estudiante','$2a$10$2wTzbbs2VCM6LRQ7e2gnb.HW/rBAmO391DGnEgd.f9O/pJTQD60g2' , 3);
           
INSERT INTO exemplary (count_exemplary, date_of_exemplary, id_book) VALUES (50, '2020-07-02', 1);
INSERT INTO exemplary (count_exemplary, date_of_exemplary, id_book) VALUES (100, '2020-07-02', 2);




