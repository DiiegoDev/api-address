# API REST para Gerenciamento de Endereços

Esta é uma API REST desenvolvida em Java com Spring Boot para gerenciar endereços. Ela permite cadastrar, buscar, editar e excluir endereços. 
O projeto é dockerizado, facilitando a execução em qualquer ambiente, e inclui testes unitários para garantir a qualidade do código.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Docker**
- **Docker Compose**
- **JUnit 5** (para testes unitários)
- **Mockito** (para mock de dependências em testes)

## Como Executar o Projeto

### Pré-requisitos

- Docker instalado na máquina.
- Docker Compose instalado na máquina.

### Passos para Execução

1. Clone o repositório:
```
https://github.com/DiiegoDev/api-address.git
```
Navegue até o diretório do projeto:
- Cole a url no seu terminal e pressione enter
- Entre na pasta do projeto digitando:
 ```
cd api-address
 ```

Execute o projeto com Docker Compose:
```
docker-compose up
```
Isso irá construir e iniciar os containers necessários para rodar a API.

A API estará disponível em:
```
http://localhost:8080/api/address
```
Endpoints da API
Cadastrar um Endereço
URL: POST /api/address

Body:
```
{
  "country": "Brasil",
  "state": "São Paulo",
  "city": "São Paulo",
  "neighborhood": "Jardins",
  "postalCode": "22132421",
  "street": "Rua Palmeiras",
  "number": 15
}
```
Resposta:
```
"Endereço salvo!!"
```

Atualizar um Endereço
URL: PUT /api/address/{id}

Body: (Pelo menos um dos campos abaixo)
```
{
  "country": "Brasil",
  "state": "Rio de Janeiro",
  "city": "Rio de Janeiro",
  "neighborhood": "Copacabana",
  "postalCode": "22070001",
  "street": "Avenida Atlântica",
  "number": 100
}
```
Resposta:
```
"Endereço atualizado!!"
```
Excluir um Endereço
URL: DELETE /api/address/{id}

Resposta:
```
"Endereço apagado!!"
```

## Estrutura do Projeto
- src/main/java: Contém o código fonte da aplicação.

- src/main/resources: Contém arquivos de configuração e propriedades.

- docker-compose.yml: Arquivo de configuração do Docker Compose para rodar a aplicação.

- Dockerfile: Arquivo de configuração do Docker para construir a imagem da aplicação.
