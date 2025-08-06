# SimpleGamemode

Um plugin leve e configurável para Spigot que permite aos jogadores mudar seu modo de jogo usando o comando `/gm` — com restrições opcionais definidas em um arquivo de configuração simples.

## Funcionalidades

- Suporte a `/gm <0|1|2|3>` para troca rápida de modo de jogo:
  - `0`: Sobrevivência
  - `1`: Criativo
  - `2`: Aventura
  - `3`: Espectador
- Bloqueia modos de jogo específicos via `config.yml`
- Acesso baseado em permissão (`simplegamemode.use`)
- Suporte a mensagens em **Português (pt)** e **Inglês (en)** via `messages.yml`
- Implementação limpa e minimalista
- Totalmente open-source

## Captura de Tela

![SimpleGamemode em ação](./assets/simplegamemode-demo.png) <!-- Imagem opcional -->

## ⚙️ Configuração

```yaml
# config.yml
blocked-gamemodes:
- creative
- spectator
```

Você pode bloquear qualquer um dos seguintes modos de jogo adicionando-os à lista:
- `survival`
- `creative`
- `adventure`
- `spectator`

## 🗣️ Suporte a Idiomas

O plugin suporta múltiplos idiomas via `messages.yml`.

Para mudar o idioma, abra `messages.yml` e defina:

```yaml
language: pt  # ou 'en'
```

Idiomas disponíveis:
- `en` → Inglês (padrão)
- `pt` → Português

## Permissões

| Permissão | Descrição | Padrão |
|--------------------------|---------------------------------|---------|
| `simplegamemode.use` | Permite usar o comando `/gm` | `op` |

## Comandos

| Comando | Descrição |
|------------------|------------------------------|
| `/gm <0|1|2|3>` | Troca seu modo de jogo |

## Uso

- Apenas jogadores podem usar o comando.
- O plugin verifica se o modo de jogo escolhido está bloqueado no `config.yml`.
- Mostra mensagens informativas e evita uso indevido.

## Requisitos

- Servidor Minecraft: **1.20.1+**
- Java: **17+**
- Testado com **Spigot** e **Purpur**

## Instalação

1. Baixe a última versão `.jar` na [página de Releases](https://github.com/your-username/SimpleGamemode/releases).
2. Coloque o arquivo na pasta `plugins/` do seu servidor.
3. Inicie o servidor.
4. Personalize o `config.yml` e o `messages.yml` conforme necessário.
5. Dê a permissão adequada aos jogadores.

## Instruções de Build (Maven)

Para compilar o plugin localmente:

```bash
git clone https://github.com/your-username/SimpleGamemode.git
cd SimpleGamemode
mvn clean package
```

O arquivo `.jar` será gerado no diretório `target/`.

## Estrutura de Arquivos

```
SimpleGamemode/
├── src/
│   └── main/
│       ├── java/
│       │   └── dev/fndit/simplegamemode/SimpleGamemodePlugin.java
│       └── resources/
│           ├── plugin.yml
│           ├── config.yml
│           └── messages.yml
├── pom.xml
└── README.md
```

## Licença

Este projeto é open-source e licenciado sob a [Licença MIT](LICENSE).

---

Feito com ❤️ por [4snt](https://github.com/4snt)
