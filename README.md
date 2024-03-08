# Encontrador de Palíndromos em Matriz - Spring Boot | Java 17
Passe uma matriz quadrada e obtenha a lista de palavras encontradas

## Requisitos funcionais
### Encontre palíndromos:
Passando uma matriz quadrada, de até 10 linhas e 10 colunas, a API retornará a lista
de palavras que são palindrômicas.

```http
POST /matches/findPalindromes
```
```json5
// body
[
  ["a", "o", "s", "s", "o"],
  ["y", "r", "z", "x", "l"],
  ["j", "s", "a", "p", "m"],
  ["j", "k", "p", "r", "z"],
  ["y", "l", "e", "r", "a"]
]
```

#### Resultado esperado:
```json5
[
    "OSSO",
    "YJJY",
    "LPPL",
    "ARARA"
]
```

### Liste as partidas e as palavras encontradas:
Obtenha os resultados de cada tentativa e as palavras palindrômicas encontradas.

```http
GET /matches
```

#### Resultado esperado:
```json5
[
  {
    "id": "19b0402f-07fb-4e97-88c3-c69090a2a890",
    "matchDate": "2024-03-05T18:56:45.343Z",
    "findWords": "osso,yjjy,arara,lppl"
  }
]
```

### Pesquise as partidas pela incidência de uma palavra específica
Obtenha a lista de partidas em que uma palavra específica aparece

**OBS**: Passe na ordem correta em que o registro está salvo!

```http
GET /matches?word=osso
```

#### Resultado esperado:
```json5
[
  {
    "id": "355c64c0-829c-451c-bc11-a61b5100b3f7",
    "matchDate": "2024-03-05T18:56:45.343Z",
    "findWords": "osso,yjjy,arara,lppl"
  },
  {
    "id": "7d3efb0d-145d-430b-aab8-3be0577f2c35",
    "matchDate": "2024-03-05T19:10:54.871Z",
    "findWords": "hjjh,abba,osso"
  }
]
```

## Requisitos Não-funcionais
- Java 17
- Spring Boot 3.2.3
- H2 Database
- Mockito
- JUnit 5
- Instancio
- JaCoCo

## Arquitetura utilizada
- Hexagonal Architecture

## Design Patterns utilizados
- Adapter
- Dependency Injection (DI)

## Instalação e utilização
```shell
mvn spring-boot:run
```

## Executando testes
```shell
mvn clean test
```

Se quiser executar os testes e, simultaneamente, gerar os documentos de cobertura dos testes, execute o seguinte comando:
```shell
mvn clean test jacoco:report
```
