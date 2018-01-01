# TargonNoNickChange

Plugin utilizado para prevenir que jogadores não sejam invadidos.

## Instalação

Coloque o plugin na pasta, abra o servidor e gere os arquivos necessários.

### Configuração

As configurações do plugin estão disponiveis no arquivo **Config.yml** contido na pasta do plugin.

*Pre-visualização do arquivo:*
```
Storage:

  #Caso esteja false, irá usar SQLite.
  useMySQL: false

  #Credenciais do Servidor MySQL, ignore se estiver usando SQLite.
  ip: localhost
  database: database
  username: root
  password: pass

Cache:

  #Tempo (em minutos) que o plugin salvará os dados no MySQL.
  timeInMinutes: 15

Messages:

  #Mensagem de Kick
  kickWrongNick:
    - '&6&lTargonNoNickChange'
    - '         &6Prezado &f{wrong_nick}&6, o seu nick ja esta registrado como &f{right_nick}&6,'
    - '  &6portanto nao podera entrar. '
```

## Database

* **SQLite** - cria um arquivo .db no local *TargonNoNickChange/database/*. *(padrão)*
* **MySQL** - Conecta em um servidor MySQL identificado pelas credenciais do arquivo de configuração. *(ativável)*

**IMPORTANTE:** Para trocar entre os modos de database, é preciso configurar a sessão *useMySQL* do arquivo de configuração.

## Comandos

* **/NoNickChange *help*** - Mostra a pagina de ajuda do plugin.
* **/NoNickChange *delete [nick]*** - Deleta um usuário da database.
* **/NoNickChange *reload*** - Recarrega as configurações do plugin.

## Autor

Plugin criado por **[Atlvntis](http://gamersboard.com.br/user/17772-atlvntis/)**.  
Agradecimentos especiais ao **[Hard](http://gamersboard.com.br/user/14128-hard/)** por revisa-lo.

## Download

Download disponível no tópico da [GamersBoard](http://gamersboard.com.br/).  
Link: [Clique Aqui](http://gamersboard.com.br/topic/53228-targonstore-targonnonickchange-previna-invas%C3%B5es-de-contas/)
