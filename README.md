<h1 align="center"> Jornada Milhas API </h1>

![Badge Em Desenvolvimento](https://img.shields.io/static/v1?label=Status&message=Em%20Desenvolvimento&color=yellow&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)
![Badge Springboot](https://img.shields.io/static/v1?label=Springboot&message=v3.1.1&color=brightgreen&style=for-the-badge&logo=springboot)
![Badge MongoDB](https://img.shields.io/static/v1?label=MongoDB&message=v6.0.7&color=47A248&style=for-the-badge&logo=MongoDB)

## :book: Resumo do projeto

REST API de uma plataforma de turismo, onde pode-se pesquisar e visualizar destinos,
preço médio de viagens, depoimentos de outros viajantes e muito mais.

O projeto está em sua primeira semana de desenvolvimento, em que devemos implementar um CRUD do recurso Depoimento,
configurar CORS, e criar testes automatizados que testem o status code das requisições desenvolvidas até agora.

Projeto proposto pela Alura no Challenge Backend 7ª Edição.

## :toolbox: Tecnologias e ferramentas

<a href="https://www.jetbrains.com/idea/" target="_blank"><img src="https://img.shields.io/badge/intellij-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white" target="_blank"></a>

<a href="https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)" target="_blank"><img src="https://img.shields.io/badge/java%2017-D32323.svg?&style=for-the-badge&logo=java&logoColor=white" target="_blank"></a>

<a href="https://spring.io/projects/spring-boot" target="_blank"><img src="https://img.shields.io/badge/Springboot-6db33f.svg?&style=for-the-badge&logo=springboot&logoColor=white" target="_blank"></a>
<a href="https://spring.io/projects/spring-data-mongodb" target="_blank"><img src="https://img.shields.io/badge/Spring%20Data%20MongoDB-6db33f.svg?&style=for-the-badge&logo=spring&logoColor=white" target="_blank"></a>

<a href="https://maven.apache.org/" target="_blank"><img src="https://img.shields.io/badge/Apache%20Maven-b8062e.svg?&style=for-the-badge&logo=apachemaven&logoColor=white" target="_blank"></a>

<a href="https://tomcat.apache.org/" target="_blank"><img src="https://img.shields.io/badge/Apache%20Tomcat-F8DC75.svg?&style=for-the-badge&logo=apachetomcat&logoColor=black" target="_blank"></a>

<a href="https://www.docker.com/" target="_blank"><img src="https://img.shields.io/badge/Docker-2496ED.svg?&style=for-the-badge&logo=docker&logoColor=white" target="_blank"></a>
<a href="https://www.mongodb.com/" target="_blank"><img src="https://img.shields.io/badge/MongoDB-47A248.svg?&style=for-the-badge&logo=MongoDB&logoColor=white" target="_blank"></a>

<a href="https://projectlombok.org/" target="_blank"><img src="https://img.shields.io/badge/Lombok-a4a4a4.svg?&style=for-the-badge&logo=lombok&logoColor=black" target="_blank"></a>
<a href="https://beanvalidation.org/" target="_blank"><img src="https://img.shields.io/badge/Jakarta%20Bean%20Validation-a4a4a4.svg?&style=for-the-badge&logo=Jakarta&logoColor=black" target="_blank"></a>
<a href="https://hibernate.org/validator/" target="_blank"><img src="https://img.shields.io/badge/Hibernate%20Validator-59666C.svg?&style=for-the-badge&logo=hibernate&logoColor=white" target="_blank"></a>

<a href="https://junit.org/junit5/" target="_blank"><img src="https://img.shields.io/badge/JUnit%205-25A162.svg?&style=for-the-badge&logo=junit5&logoColor=white" target="_blank"></a>
<a href="https://site.mockito.org/" target="_blank"><img src="https://img.shields.io/badge/Mockito-C5D9C8.svg?&style=for-the-badge" target="_blank"></a>
<a href="https://www.postman.com/" target="_blank"><img src="https://img.shields.io/badge/postman-ff6c37.svg?&style=for-the-badge&logo=postman&logoColor=white" target="_blank"></a>
<a href="https://en.wikipedia.org/wiki/Unit_testing" target="_blank"><img src="https://img.shields.io/badge/Unit%20Tests-5a61d6.svg?&style=for-the-badge&logo=unittest&logoColor=white" target="_blank"></a>

## :bulb: Funcionalidades

### API de gerenciamento de Depoimentos (Statement)

- `Salvar`: Salvar um depoimento através de um **POST /api/statements** com as informações *username*, *text* e
  *urlImage*
  em um JSON no corpo da requisição. Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "username" : "Lorem Ipsum",
      "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
      "urlImage" : "https://xptoimages.com/1234567.jpg"
    }
    ```

  Em caso de sucesso a resposta tem status 201 com um JSON no corpo da resposta contendo
  **id**, **username**, **text**, **urlImage** e **createdAt** do depoimento salvo. Segue abaixo um exemplo do corpo da
  resposta.
    ```json
    {
      "id" : "1234567890abcdef12345678",
      "username" : "Lorem Ipsum",
      "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
      "urlImage" : "https://xptoimages.com/1234567.jpg",
      "createdAt": "2023-07-19T14:03:24"
    }
    ```


- `Busca paginada`: Busca paginada de depoimentos através de um **GET /api/statements**. O cliente decide qual página 
  e quantidade de dados deseja, assim como o modo de ordenação, basta adicionar os parâmetros na url da requisição, 
  ex: **/api/statements?page=4&size=3&sort=createdAt,DESC**.<br><br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo os depoimentos solicitados.
  Segue abaixo um exemplo do corpo da resposta.
    ```json
    {
      "content": [
        {
          "id" : "1234567890abcdef1234567a",
          "username" : "Lorem Ipsum",
          "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
          "urlImage" : "https://xptoimages.com/123456a.jpg",
          "createdAt": "2023-07-19T15:00:00"
        },
        {
          "id" : "1234567890abcdef1234567b",
          "username" : "Dolor Sit",
          "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
          "urlImage" : "https://xptoimages.com/123456b.jpg",
          "createdAt": "2023-07-19T14:00:00"
        },
        {
          "id" : "1234567890abcdef1234567c",
          "username" : "Amet Consectetur",
          "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
          "urlImage" : "https://xptoimages.com/123456c.jpg",
          "createdAt": "2023-07-19T13:00:00"
        }
      ],
      "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 12,
        "pageNumber": 4,
        "pageSize": 3,
        "paged": true,
        "unpaged": false
      },
      "totalPages": 11,
      "totalElements": 31,
      "last": true,
      "size": 3,
      "number": 4,
      "sort": {
        "empty": true,
        "sorted": true,
        "unsorted": false
      },
      "numberOfElements": 3,
      "first": false,
      "empty": false
    }
    ```


- `Busca por id`: Busca depoimento por ID através de um **GET /api/statements/{ID}**, onde *{ID}* é o identificador do
  Depoimento.<br><br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo o depoimento solicitado.
  Segue abaixo um exemplo do corpo da resposta.
  ```json
  {
    "id" : "1234567890abcdef12345678",
    "username" : "Lorem Ipsum",
    "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
    "urlImage" : "https://xptoimages.com/1234567.jpg",
    "createdAt": "2023-07-19T14:03:24"
  }
  ```
  
- `Busca para home`: Busca os 3 depoimentos mais recentes através de um **GET /api/statements/home**.<br><br>
  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo os 3 depoimentos mais recentes.
  Segue abaixo um exemplo do corpo da resposta.
  ```json
  [
     {
      "id" : "1234567890abcdef1234567a",
      "username" : "Lorem Ipsum",
      "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
      "urlImage" : "https://xptoimages.com/123456a.jpg",
      "createdAt": "2023-07-19T15:00:00"
    },
    {
      "id" : "1234567890abcdef1234567b",
      "username" : "Dolor Sit",
      "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
      "urlImage" : "https://xptoimages.com/123456b.jpg",
      "createdAt": "2023-07-19T14:00:00"
    },
    {
      "id" : "1234567890abcdef1234567c",
      "username" : "Amet Consectetur",
      "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
      "urlImage" : "https://xptoimages.com/123456c.jpg",
      "createdAt": "2023-07-19T13:00:00"
    }
  ]
  ```

- `Atualizar`: Atualizar Depoimento através de um **PUT /api/statements/{ID}**, onde *ID* é o identificador do Statement,
  os novos dados do depoimento devem ser enviados no corpo da requisição. Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "username" : "Lorem I. Dolor",
      "text" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget arcu condimentum, pulvinar sem non, convallis felis.",
      "urlImage" : "https://xptoimages.com/1234567890.jpg"
    }
    ```

  Em caso de sucesso a resposta tem status 204.


- `Deletar`: Deletar depoimento através de um **DELETE /api/statements/{ID}**, onde *{ID}* é o identificador do
  Depoimento.<br>

  Em caso de sucesso a resposta tem status 204.