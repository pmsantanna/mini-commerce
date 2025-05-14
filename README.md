# 🛒 MiniCommerce – Vue + Spring Boot + JWT

MiniCommerce é um projeto fullstack simples com **Spring Boot no backend** e **Vue 3 + Vite no frontend**, com suporte a autenticação JWT, cadastro de produtos e simulação de pedidos.

---

## 📁 Estrutura do Projeto

```
/mini-commerce
├── /backend       # API REST com Spring Boot + H2 + JWT
└── /frontend      # SPA com Vue 3 + Vite + Axios
```

---

## 🚀 Como Rodar Localmente

### 🔧 Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Node.js 16+](https://nodejs.org/)
- [Maven](https://maven.apache.org/)

---

### ▶️ Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```

A API estará rodando em:  
👉 `http://localhost:8080`

Acesse o console do H2:  
👉 `http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:mem:minicommerce`

---

### 🌐 Frontend (Vue 3 + Vite)

```bash
cd frontend
npm install
npm run dev
```

Frontend disponível em:  
👉 `http://localhost:5173`

---

## 🔐 Login de Administrador

Usuário padrão criado automaticamente:

```
Email:    admin@admin.com
Senha:    123456
```

---

## 🧪 Endpoints Principais

### Autenticação

```http
POST /api/auth/login
```

**Body:**

```json
{
  "email": "admin@admin.com",
  "password": "123456"
}
```

**Resposta:**

```json
{
  "token": "JWT_TOKEN_AQUI"
}
```

---

### Produtos

- `GET /api/products` – lista todos os produtos (público)
- `POST /api/products` – cria novo produto (requer JWT)
- `DELETE /api/products/{id}` – remove produto (JWT)

---

### Pedidos (Orders)

- `POST /api/orders` – cria pedido com lista de produtos
- `GET /api/orders` – lista todos os pedidos
- `GET /api/orders/{id}` – detalhes de um pedido

---

## 🛡️ Segurança

- JWT gerado via `/api/auth/login`
- Requisições protegidas devem incluir no header:

```
Authorization: Bearer <token>
```

---

## 🎯 Tecnologias Utilizadas

### Backend:

- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- H2 (banco em memória)
- BCrypt para senha

### Frontend:

- Vue 3
- Vite
- Vue Router
- Axios

---

## 🧩 Melhorias Futuras

- Upload de imagens
- Integração com Stripe ou PagSeguro
- Painel administrativo completo
- Cadastro de usuários

---

## 📃 Licença

MIT © Pedro Santanna
