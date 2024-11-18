# Library Management

Sistema de Gerenciamento de Biblioteca 

Este projeto é uma aplicação para gerenciar bibliotecas, desenvolvida com foco em organização e padronização. Ele utiliza **Docker** para instanciar um container com o banco de dados MySQL.

---

## 🛠 Tecnologias Utilizadas
- **MySQL**: Banco de dados relacional.
- **Docker**: Para criar e gerenciar o container do banco de dados.
- **Postman**: Para testes das APIs.
- **DBeaver**: Para visualização das tabelas.
- **Java Spring Boot** : Back-end da aplicação.

---

## 🚀 Como Rodar o Projeto

### 1. Subir o Banco de Dados
Execute o comando abaixo para iniciar o container do banco:
```bash
docker-compose up -d
```

- O banco de dados será automaticamente populado via script incluído no projeto.

### 2. Testar a API
- Uma **collection do Postman** está disponível na pasta `postman/` para facilitar os testes das rotas da aplicação.
- Prints de exemplo dos testes também estão disponíveis na mesma pasta.

---

## 📂 Estrutura do Projeto

- **Tratamento de Exceções**:
  - Um arquivo dedicado foi criado para lidar com exceções, garantindo que mensagens detalhadas sejam enviadas aos usuários.
  
- **Classe de Resposta Padronizada**:
  - Uma classe foi implementada para padronizar as respostas da API. Ela contém:
    - **Status Code**: Código HTTP da resposta.
    - **Message**: Mensagem descritiva sobre a operação.
    - **Content**: Dados retornados pela API.

---

Sinta-se à vontade para contribuir, reportar qualquer problema ou propor melhorias! 😊