# Backend Application

## Principais Requisitos Funcionais

- **Cadastro de Categoria**: Permitir o cadastro, edição e exclusão de categorias.
- **Cadastro de Produto**: Permitir o cadastro, edição e exclusão de produtos, com associação a categorias.
- **Listagem de Categorias**: Listar todas as categorias cadastradas.
- **Listagem de Produtos**: Listar todos os produtos cadastrados.

## Principais Requisitos Não Funcionais

- **Autenticação e Autorização**: Sistema de login baseado em JWT para segurança das APIs.
- **Documentação da API**: A API será documentada automaticamente com Swagger para facilitar o uso e integração.
- **Persistência**: Utilização de um banco de dados relacional (MySQL) para armazenar os dados.

## Como Subir a Aplicação

### Pré-requisitos

- Java 21
- Maven
- Docker

### Passo a Passo

1. Clone o repositório da aplicação:
   ```bash
   git clone https://github.com/sidartaoss/backend-application.git
   cd backend-application
    ```

2. Instale as dependências do projeto:
   ```bash
   mvn clean install
   ```

3. Rode a aplicação localmente com o comando:
   ```bash
   mvn spring-boot:run
   ```
   
4. A aplicação estará disponível no endpoint padrão: http://localhost:8080/api/actuator/health

### Subindo a Base de Dados (MySQL)

Para rodar o banco de dados (MySQL) no Docker, você pode usar os seguintes comandos:

   ```bash
   docker compose up -d
   ```
### Acesso às Rotas Protegidas da API

Para acessar as rotas da API que exigem autenticação, é necessário incluir um token de autenticação no cabeçalho da requisição. Esse token é gerado após o login ou outro processo de autenticação e deve ser enviado para autorizar o acesso.

### Passos para enviar o token no Postman:

1. Abra o Postman e selecione a requisição que deseja fazer.

2. Vá para a aba Authorization.

3. No campo Type, selecione Bearer Token.

4. No campo Token, insira o token gerado ou obtido após a autenticação.

5. Exemplo de configuração no Postman:
- Authorization → Bearer Token
- Token: [Informe o seu token aqui]

### Obtendo o Token de Autenticação

#### 1. Instalar o Python (se ainda não tiver):

   ```bash
   python3 --version
   ```
#### 2. Criar um arquivo para o código

Abra o terminal (ou Prompt de Comando no Windows) e crie um arquivo chamado gerar_token.py digitando:

    ```bash
    nano gerar_token.py  # No Linux/macOS
    notepad gerar_token.py  # No Windows
    ```

Agora, copie e cole o código abaixo no arquivo e salve:

```python
from datetime import datetime, timedelta
from jose import jwt

# Chave secreta (deve ter pelo menos 32 caracteres para HMAC)
SECRET_KEY = "0123456789abcdef0123456789abcdef"

# Configurações do token
ALGORITHM = "HS256"
EXPIRATION_TIME_MINUTES = 60  # 1 hora

# Criar claims do token
claims = {
    "sub": "admin",
    "iat": datetime.utcnow(),
    "exp": datetime.utcnow() + timedelta(minutes=EXPIRATION_TIME_MINUTES)
}

# Gerar token JWT
token = jwt.encode(claims, SECRET_KEY, algorithm=ALGORITHM)

print("Token JWT gerado:", token)
```

#### 3. Instalar a biblioteca necessária

No terminal, instale a biblioteca python-jose com o seguinte comando:

```bash
pip3 install python-jose
```

#### 4. Executar o código

Agora, execute o código Python para gerar o token JWT com o comando:

```bash
python3 gerar_token.py
```

Isso imprimirá no terminal um token JWT válido com "sub": "admin" e expiração de 1 hora.

🚀 Agora é só copiar e usar!


### Endpoints da API

A API está documentada automaticamente no Swagger. Você pode acessar a documentação da API através do seguinte URL:

- http://localhost:8080/api/swagger-ui.html

### Considerações Finais

A aplicação foi construída utilizando Spring Boot e segue a arquitetura MVC.
Utilize as rotas da API conforme as instruções de documentação no Swagger.