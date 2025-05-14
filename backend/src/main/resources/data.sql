INSERT INTO category (id, name) VALUES (1, 'Eletrônicos'), (2, 'Livros');

INSERT INTO product (name, description, price, image_url, category_id)
VALUES
  ('Smartphone Z1', 'Celular com ótimo custo-benefício', 1999.90, 'img/smartphone.jpg', 1),
  ('Livro Java', 'Aprenda Spring Boot do zero ao avançado', 89.90, 'img/livro-java.jpg', 2);
