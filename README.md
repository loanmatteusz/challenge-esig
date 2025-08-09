# Projeto Todo - Desafio Técnico ESIG

Projeto simples de gerenciamento de tarefas (Todo) com backend em Spring Boot e frontend em Angular.

## Estrutura do projeto

- `/backend` - API REST em Spring Boot com Spring Security e documentação Swagger
- `/frontend` - Aplicação Angular
- `docker-compose.yml` - Orquestração dos containers para backend e frontend

## Funcionalidades

- Registro e login de usuários com segurança (Spring Security)
- CRUD completo de tarefas (Tasks)
- Listagem de usuários
- Documentação da API disponível via Swagger

## Deploys
- Backend (Render): https://challenge-esig.onrender.com/swagger-ui/index.html
- Frontend (Vercel): https://challenge-esig.vercel.app/

## Como rodar

1. Clone o repositório

```bash
git clone https://github.com/loanmatteusz/challenge-esig
cd challenge-esig
```

2. Execute o docker-compose para subir backend e frontend:
```bash
docker-compose up --build
```

3. Acesse o frontend no navegador:
```bash
http://localhost:4200
```

4. Documentação da API via Swagger
```bash
http://localhost:8080/swagger-ui/index.html
```

### Tecnologias
- Java 21 / Spring Boot
- Spring Security
- Swagger (OpenAPI)
- Angular 19
- Docker / Docker Compose
