# Solução

Para resolver esse desafio, implementei uma solução que utiliza técnicas de criptografia para proteger os dados sensíveis. A criptografia é realizada em tempo de execução durante a conversão da entidade para a representação no banco de dados e vice-versa.

## Exemplo dos dados incluído no Banco de Dados

![Captura de tela 2024-02-26 182244](https://github.com/Viniciushfc/cryptography-java-spring/assets/89172416/f5c0811b-cf2a-4bbf-a4d8-b0d77a17940f)


Nesse desafio foi utilizado as seguintes tecnologias:

- Spring Web
- Spring JPA
- Spring Security
- Spring Devtools
- Lombok
- PostgreSQL

## Como usar

Baixe o projeto em sua máquina, abra em um IDE de sua preferencia (exemplo: Intellij), configure sua aapplication propertie, suas propriedades do seu postgresql.

(Detalhe para você visualizar os dados criptografados você devera abrir o pgAdmin).

## Endpoints

- POST: http://localhost:8080/users/create (Endpoint para criar um User);
- PUT: http://localhost:8080/users/update/{id} (Endpoint para atualizar um User);
- GET: http://localhost:8080/users (Endpoint para buscar todos os User cadastrados);
- GET: http://localhost:8080/users/{id} (Endpoint para buscar um User específicopor ID);
- DELETE: http://localhost:8080/users/delete/{id} (Endpoint para deletar um User específico por ID);

## Criptografia

Seu desafio será implementar a criptografia em um serviço de forma transparente para a API e para as camadas de
serviço de sua aplicação. O objetivo é garantir que os campos sensíveis dos objetos de entidade não sejam visíveis
diretamente, realizando a criptografia em tempo de execução durante a conversão da entidade para a coluna correspondente
no banco de dados, e vice-versa.

## Exemplo

Considere os campos `userDocument` e `creditCardToken` como campos sensíveis que devem ser criptografados. A tabela de
exemplo seria a seguinte:

| id | userDocument     | creditCardToken | value |
|:---|:-----------------|:----------------|:------|
| 1  | MzYxNDA3ODE4MzM= | YWJjMTIz        | 5999  |
| 2  | MzI5NDU0MTA1ODM= | eHl6NDU2        | 1000  |
| 3  | NzYwNzc0NTIzODY= | Nzg5eHB0bw==    | 1500  |

A estrutura da entidade correspondente seria a seguinte:

| Campo           | Tipo   |
|:----------------|:-------|
| id              | Long   |
| userDocument    | String |
| creditCardToken | String |
| value           | Long   |

## Requisitos

- Implemente um CRUD simples considerando os campos mencionados acima como sensíveis.
- Utilize o algoritmo de criptografia de sua preferência. Sugestões: [SHA-512](https://en.wikipedia.org/wiki/SHA-2) ou
  [PBKDF2](https://en.wikipedia.org/wiki/PBKDF2).
