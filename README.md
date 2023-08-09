<h1 align="center"> Jornada Milhas API </h1>

![Badge Em Desenvolvimento](https://img.shields.io/static/v1?label=Status&message=Em%20Desenvolvimento&color=yellow&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)
![Badge Springboot](https://img.shields.io/static/v1?label=Springboot&message=v3.1.1&color=brightgreen&style=for-the-badge&logo=springboot)
![Badge MongoDB](https://img.shields.io/static/v1?label=MongoDB&message=v6.0.7&color=47A248&style=for-the-badge&logo=MongoDB)

## :book: Resumo do projeto

REST API de uma plataforma de turismo, onde pode-se pesquisar e visualizar destinos,
preço médio de viagens, depoimentos de outros viajantes e muito mais.

O projeto está em sua terceira semana de desenvolvimento, em que devemos: 
- Modificar o endpoint de criação de destino para que aceite: 
  - duas **imagens** 
  - um campo **meta** para meta descrição
  - um campo **description** para um resumo do destino (opcional) 
- Desenvolver um endpoint para buscar mais detalhes sobre um destino
- Integrar o **ChatGPT** para quando **description** não for informado, assim, o ChatGPT deverá gerar um resumo do 
  destino que será salvo

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
<a href="https://openai.com/" target="_blank"><img src="https://img.shields.io/badge/Open%20AI-412991.svg?&style=for-the-badge&logo=openai&logoColor=white" target="_blank"></a>

<a href="https://junit.org/junit5/" target="_blank"><img src="https://img.shields.io/badge/JUnit%205-25A162.svg?&style=for-the-badge&logo=junit5&logoColor=white" target="_blank"></a>
<a href="https://site.mockito.org/" target="_blank"><img src="https://img.shields.io/badge/Mockito-C5D9C8.svg?&style=for-the-badge" target="_blank"></a>
<a href="https://www.postman.com/" target="_blank"><img src="https://img.shields.io/badge/postman-ff6c37.svg?&style=for-the-badge&logo=postman&logoColor=white" target="_blank"></a>
<a href="https://en.wikipedia.org/wiki/Unit_testing" target="_blank"><img src="https://img.shields.io/badge/Unit%20Tests-5a61d6.svg?&style=for-the-badge&logo=unittest&logoColor=white" target="_blank"></a>

<a href="https://springdoc.org/" target="_blank"><img src="https://img.shields.io/badge/Spring%20Doc-85EA2D.svg?&style=for-the-badge" target="_blank"></a>
<a href="https://swagger.io/" target="_blank"><img src="https://img.shields.io/badge/Swagger-85EA2D.svg?&style=for-the-badge&logo=swagger&logoColor=black" target="_blank"></a>

## :bulb: Funcionalidades

### API de gerenciamento de Destinos (Destination)

- `Salvar`: Salvar um destino através de um **POST /api/destinations** com o *content-type* como *multipart/form-data*
  e o request body em três partes, uma com nome **destination_info** com as informações *name*, *meta*,
  *description* (opcional, caso não seja enviado uma descrição é fornecida pelo sistema através do ChatGPT)
  e *price* em um JSON, a segunda parte com nome **destination_image1** com um arquivo de imagem **JPEG** ou **PNG**,
  a terceira parte com nome **destination_image2** com um arquivo de imagem **JPEG** ou **PNG**.
  Segue abaixo um exemplo do corpo da requisição.

  ```
  POST /api/destinations HTTP/1.1
  Content-Length: 428
  Content-Type: multipart/form-data; boundary=--BOUNDARY
  
  --BOUNDARY
  Content-Type: application/json
  Content-Disposition: form-data; name="destination_info"
  
  {
    "name": "Veneza - Itália",
    "meta": "Uma bela cidade da Itália",
    "description": "descrição mais detalhada da cidade",
    "price": 550.00
  }
    
  --BOUNDARY
  Content-Type: image/png
  Content-Disposition: form-data; name="destination_image1"; filename="veneza.png"
  
  (Content of your image file)
  
  --BOUNDARY
  Content-Type: image/png
  Content-Disposition: form-data; name="destination_image2"; filename="veneza.png"
    
  (Content of your image file)
  --BOUNDARY--
  ```

  Em caso de sucesso a resposta tem status 201 com um JSON no corpo da resposta contendo **id**, **name**,
  **meta**, **description**, **price**, **images** e **createdAt** do destino salvo. Segue abaixo um exemplo do corpo da resposta.

    ```json
    {
      "id" : "1234567890abcdef12345678",
      "name" : "Veneza - Itália",
      "price" : 550.00,
      "meta" : "Uma bela cidade da Itália",
      "description": "descrição mais detalhada da cidade",
      "images" : [
          "https://xptoimages.com/1234567.jpg",
          "https://xptoimages.com/1234567.jpg"
      ],
      "createdAt": "2023-07-30T14:03:24"
    }
    ```

- `Busca paginada`: Busca paginada de destinos através de um **GET /api/destinations**. O cliente decide qual página,
  quantidade de dados, e modo de ordenação, basta adicionar os parâmetros na url da requisição. Também pode-se buscar por
  nome do destino, também adicionando o parâmetro *name* com o nome do destino desejado na url da requisição.
  ex: **/api/destinations?page=0&size=3&name=porto**.<br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo os destinos encontrados.
  Segue abaixo um exemplo do corpo da resposta.

    ```json
    {
      "content": [
        {
          "id" : "1234567890abcdef1234567a",
          "name" : "Porto Alegre - RS",
          "price" : 650.00,
          "image" : "https://xptoimages.com/poa.jpg",
          "createdAt": "2023-07-19T15:00:00"
        },
        {
          "id" : "1234567890abcdef1234567b",
          "name" : "Porto Velho - RO",
          "price" : 775.00,
          "image" : "https://xptoimages.com/portovelho.jpg",
          "createdAt": "2023-07-19T14:00:00"
        },
        {
          "id" : "1234567890abcdef1234567c",
          "name" : "Porto - Portugal",
          "price" : 1500.00,
          "image" : "https://xptoimages.com/porto.png",
          "createdAt": "2023-07-19T13:00:00"
        }
      ],
      "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 3,
        "paged": true,
        "unpaged": false
      },
      "totalPages": 3,
      "totalElements": 8,
      "last": false,
      "size": 3,
      "number": 0,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "numberOfElements": 3,
      "first": true,
      "empty": false
    }
    ```

- `Busca por id`: Busca destino por ID através de um **GET /api/destinations/{ID}**, onde *{ID}* é o identificador do
  Destino.<br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo o destino solicitado.
  Segue abaixo um exemplo do corpo da resposta.

  ```json
  {
    "id" : "1234567890abcdef12345678",
    "name" : "Veneza - Itália",
    "price" : 550.00,
    "urlImage" : "https://xptoimages.com/1234567.jpg",
    "createdAt": "2023-07-19T14:03:24"
  }
  ```

- `Atualizar`: Atualizar Destino através de um **PUT /api/destinations/{ID}**, onde *ID* é o identificador do Destino,
  os novos dados do destino devem ser enviados através de um JSON no corpo da requisição,
  com as informações *name*, *meta*, *description* (opcional, caso não seja enviado uma descrição é fornecida pelo sistema através do ChatGPT),
  e *price*. Segue abaixo um exemplo do corpo da requisição.

  ```json
  {
    "name": "Veneza - Itália",
    "meta": "Uma bela cidade da Itália",
    "description": "descrição mais detalhada da cidade",
    "price": 550.00
  }
  ```
  
- `Atualizar imagem`: Atualizar imagem do destino através de um **PATCH /api/destinations/{destinationId}/images/{imagesId}**,
  onde **destinationId** é o identificador do destino e **imageId** é o identificador da imagem. O *content-type* deve 
  ser *multipart/form-data* e a imagem deve ser enviada no corpo da requisição em uma parte com chave **destination_image**.

  Em caso de sucesso a resposta tem status 204.

- `Deletar`: Deletar destino através de um **DELETE /api/destinations/{ID}**, onde *{ID}* é o identificador do
  Destino.<br>

  Em caso de sucesso a resposta tem status 204.

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
  ex: **/api/statements?page=4&size=3&sort=createdAt,DESC**.<br>

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
  Depoimento.<br>

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

### API de gerenciamento de Imagens (Image)

- `Buscar`: Buscar imagem através de um **GET /api/images/{ID}**, onde **ID** é o identificador da imagem.

  Em caso de sucesso a resposta tem status 200 com um arquivo binário do tipo image/jpeg ou image/png no corpo da resposta.

## :computer: Executar a aplicação

### :whale: Via Docker

Clone o projeto.

```bash
git clone git@github.com:Edson-Mendes/jornada-milhas-api.git
```

A aplicação tem integração com o ChatGPT, então é necessáio gerar uma [OpenAI KEY](https://platform.openai.com/docs/api-reference/authentication).

Abra o arquivo [jornada-milhas-api.yml](https://github.com/Edson-Mendes/jornada-milhas-api/blob/main/jornada-milhas-api.yml) 
e substitua o campo **<seu-token-open-ai>** pela sua **OpenAI KEY**.

O arquivo [jornada-milhas-api.yml](https://github.com/Edson-Mendes/jornada-milhas-api/blob/main/jornada-milhas-api.yml) 
está configurado para subir um container [MongoDB](https://hub.docker.com/_/mongo)e um container 
[jornada-milhas-api](https://hub.docker.com/r/edsonmendes/jornada-milhas-api).

Execute o comando abaixo no diretório da aplicação para subir os containers.

```bash
docker compose -f jornada-milhas-api.yml up -d
```

Então acesse <localhost:8080/swagger-ui.html> para interagir com  a interface do Swagger.

### Atualizações futuras

[ ] Adicionar autenticação de usuário (estou pensando em usar OAuth2).
[ ] A urlImage do Depoimento ser a mesma imagem de perfil do usuário que cadastrar o depoimento.
[ ] Realizar o deploy da aplicação.