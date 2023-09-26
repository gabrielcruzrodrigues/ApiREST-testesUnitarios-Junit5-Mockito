# ApiREST-testesUnitarios-Junit5-Mockito
### O foco deste projeto foi colocar a prova as minhas habilidades em testes unitários, e para isso, utilizei das bibliotecas Junit 5 e Mockito.

O projeto conta com uma apiREST de pessoas, com um Crud completo, usando o banco de dados H2 para fazer a persistência, e também utilizei de alguns outros conceitos como:  

* DTO
* Beans
* DDD
* Tratamento de Exceções (Exception Handler)
* Testes unitários
* Carga inicial de dados
* ModelMapper

### O projeto está com 100% de cobertura de teste em suas classes!

<hr>

# Como rodar o projeto:
1. Tenha o JDK17 instalado na sua maquina!
2. Clone o projeto para um diretorio local.
3. Abra o projeto em sua IDE de preferência e baixe as dependências.
4. Inicie o projeto!

O projeto já vem com carga inicial de dados, então vc já vai ter alguns registros no banco para poder manipular.

### para verificar os testes e rodar os mesmos, va até: `src/test/java`

# EndPoints
#### GET /person - status: 200
Resgata todos os registros do banco de dados.
```
[
    {
        "id": 1,
        "name": "Gabriel",
        "email": "gabriel@gmail.com"
    },
    {
        "id": 2,
        "name": "Samuel",
        "email": "samuel@gmail.com"
    },
    {
        "id": 3,
        "name": "Stéfany",
        "email": "Stéfany@gmail.com"
    },
    {
        "id": 4,
        "name": "Sara",
        "email": "Sara@gmail.com"
    },
    {
        "id": 5,
        "name": "Pedro",
        "email": "Pedro@gmail.com"
    }
]
```
#### GET /person/:id - status: 200
Busca pessoa por id.
```
{
    "name": "Gabriel",
    "email": "gabriel@gmail.com",
}
```

#### POST /person - status: 201
cria nova pessoa.
```
{
    "name": "Gabriel",
    "email": "gabriel@gmail.com",
    "password": "123"
}
```
#### PUT /person/:id - status: 200
Atualiza registro no banco de dados.
```
{
    "name": "Gabriel",
    "email": "gabriel@gmail.com",
    "password": "123"
}
```
#### DELETE /person/:id - status: 204
Deleta pessoas do banco de dados.


