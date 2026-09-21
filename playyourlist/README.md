# Artur Napoles de Oliveira 
# Atividade de Microservices Development 

Implementação da atividade de Microservices Development (enunciado em
`MS-AV-01-Endpoints.md`): sistema de playlists **Play Your List**, com 4
"microsserviços" (musicas, playlists, reproducao, api) dentro do mesmo
projeto Spring Boot, conforme pedido no enunciado para simplificar a entrega.

## Arquitetura

- Um único projeto Maven / Spring Boot (porta `8080`), com um pacote por
  microsserviço: `musica`, `playlist`, `reproducao` e `api`.
- Banco de dados **H2 em memória**, populado automaticamente pelo
  `src/main/resources/data.sql` (os mesmos scripts do enunciado).
- O pacote `api` **não acessa os repositórios diretamente**: ele chama os
  endpoints `musicas`, `playlists` e `reproducao` via **Open Feign**
  (`MusicaClient`, `PlaylistClient`, `ReproducaoClient`), exatamente como
  pede o enunciado, mesmo estando tudo no mesmo processo. As URLs desses
  clients ficam em `application.properties` (`services.*.url`), então, se
  um dia você quiser separar em projetos/portas diferentes, basta trocar
  essas três linhas.
- Validações de `musicas` e `playlists` (campos obrigatórios/tamanho) são
  feitas com Bean Validation (`jakarta.validation`) nos DTOs de request.
- Erros (404, validação, falha de Feign) são tratados de forma centralizada
  em `exception/GlobalExceptionHandler.java`, retornando JSON padronizado.

> **Atenção a um ponto do enunciado:** a especificação diz que
> `PUT /api/executar/{playlistId}` deve registrar a execução chamando
> "`POST /statistic`", mas o microsserviço `reproducao` só define
> `POST /reproducao`. Implementei o `ReproducaoClient` chamando
> `POST /reproducao` (o endpoint que realmente existe). Se o professor
> confirmar que o nome correto é outro, é só ajustar o `@PostMapping` do
> `ReproducaoClient` e do `ReproducaoController`.

## Pré-requisitos

- JDK 17 ou superior
- Maven 3.9+ (ou usar o Maven integrado da extensão *Extension Pack for
  Java* / *Spring Boot Extension Pack* do VSCode)

## Como rodar no VSCode

1. Abra a pasta do projeto no VSCode.
2. Instale (se ainda não tiver) a extensão **Extension Pack for Java** e
   **Spring Boot Extension Pack**.
3. Rode pela extensão (botão *Run* sobre `PlayYourListApplication.java`)
   ou pelo terminal:

   ```bash
   mvn spring-boot:run
   ```

4. A aplicação sobe em `http://localhost:8080`.
5. Console do H2 (para inspecionar as tabelas): `http://localhost:8080/h2-console`
   — JDBC URL `jdbc:h2:mem:playyourlist`, usuário `sa`, senha em branco.

## Endpoints e exemplos de teste (curl)

### musicas

```bash
# Listar
curl http://localhost:8080/musicas

# Buscar por id
curl http://localhost:8080/musicas/1

# Cadastrar
curl -X POST http://localhost:8080/musicas \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Hotel California","artista":"Eagles","album":"Hotel California","duracao":391,"genero":"Rock"}'

# Atualizar
curl -X PUT http://localhost:8080/musicas/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Imagine (Remaster)","artista":"John Lennon","album":"Imagine","duracao":183,"genero":"Rock"}'

# Excluir
curl -X DELETE http://localhost:8080/musicas/1
```

### playlists

```bash
curl http://localhost:8080/playlists
curl http://localhost:8080/playlists/1

curl -X POST http://localhost:8080/playlists \
  -H "Content-Type: application/json" \
  -d '{"nome":"Foco no Trabalho","descricao":"HPWM"}'

curl -X PUT http://localhost:8080/playlists/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Clássicos do Rock (v2)","descricao":"Atualizada"}'

curl -X DELETE http://localhost:8080/playlists/1

# Adicionar / remover música da playlist
curl -X POST http://localhost:8080/playlists/2/musicas/3
curl -X DELETE http://localhost:8080/playlists/2/musicas/3

# Listar ids das músicas da playlist
curl http://localhost:8080/playlists/1/musicas
```

### reproducao

```bash
curl -X POST http://localhost:8080/reproducao \
  -H "Content-Type: application/json" \
  -d '{"playlistId": 1}'

curl http://localhost:8080/reproducao/1
curl http://localhost:8080/reproducao/total/1
```

### api (orquestrador via Open Feign)

```bash
# Valida playlist e música (GET nos dois microsserviços) e só então associa
curl -X POST http://localhost:8080/api/adicionar/2/musicas/3

# Valida a playlist e registra a execução via POST /reproducao
curl -X PUT http://localhost:8080/api/executar/1
```

## Regras de validação implementadas

- **Música**: `titulo` e `artista` obrigatórios (não vazios/só espaços);
  `album` até 150 caracteres; `duracao` obrigatória e > 0; `genero` até 50
  caracteres.
- **Playlist**: `nome` obrigatório (não vazio/só espaços); `descricao` até
  255 caracteres.

Qualquer violação retorna `400 Bad Request` com a lista dos campos
inválidos. IDs inexistentes retornam `404 Not Found`.
