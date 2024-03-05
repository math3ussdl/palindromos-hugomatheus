# Encontrador de Palíndromos em Matriz - Spring Boot | Java 17
Passe uma matriz quadrada e obtenha a lista de palavras encontradas 

## Requisitos funcionais
### Encontre palíndromos:
Passando uma matriz quadrada, de até 10 linhas e 10 colunas, a API retornará a lista
de palavras que são palindrômicas.

```http
POST /findPalindromes
[
  ["a", "o", "s", "s", "o"],
  ["y", "r", z", "x", "l"],
  ["j", "s", "a", "p", "m"],
  ["j", "k", "p", "r", "z"],
  ["y", "l", "e", "r", "a"]
]
```

#### Resultado esperado:
```json
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
GET /findMatches
```

#### Resultado esperado:
```json
[
  {
    "match_date": "2024-03-05T18:56:45.343Z",
    "find_words": [
      "OSSO",
      "YJJY",
      "LPPL",
      "ARARA"
    ],
    "_id": 1
  }
]
```

### Pesquise as partidas pela incidência de uma palavra específica
Obtenha a lista de partidas em que uma palavra específica aparece

```http
GET /findMatches?word=OSSO
```

#### Resultado esperado:
```json
[
  {
    "match_date": "2024-03-05T18:56:45.343Z",
    "find_words": [
      "OSSO",
      "YJJY",
      "LPPL",
      "ARARA"
    ],
    "_id": 1
  },
  {
    "match_date": "2024-03-05T19:10:54.871Z",
    "find_words": [
      "HJJH",
      "ABBA",
      "OSSO"
    ],
    "_id": 3
  }
]
```

## Requisitos Não-funcionais
- Java 17
- Spring Boot 6.1.4
- Lombok
- MongoDB
- Mockito
- JUnit 5
- JaCoCo
