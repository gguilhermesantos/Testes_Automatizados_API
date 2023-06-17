<a name="readme-top"></a>
# Testes Automatizados de API em Java

Este projeto tem o intuito de aprender a configurar e desenvolver automação de API com Rest Assured em Java.

## Inicializando

Estas instruções fornecerão uma cópia do projeto em execução em sua máquina local para fins de desenvolvimento e teste.

### Pré requisitos

* Ter conhecimento das funcionalidades da API de [teste](http://cgitar.juliodelima.com.br/)
* Ter inicializado e configurado um repositório git
* Ter instalado [IntelliJ](https://www.jetbrains.com/idea/download/)
* Baixar [JDK 8](https://www.oracle.com/br/java/technologies/javase/javase8u211-later-archive-downloads.html)

### Instalando

Para começar um novo projeto no intelliJ:
* Clicar em **New Project**
* Criar um nome
* Ajustar pasta do projeto em **Location**
* Colocar opção **Maven** em **Build system**
* Utilizar **JDK 8** já instalado ou **versão 1.8.0**

Caso opte por clonar este projeto utilizar comando:

```sh
git clone https://github.com/gguilhermesantos/Testes_Automatizados_API
```

## Rodando testes

Para rodar testes:
* Abrir qualquer arquivo dos testes em src > test > java
* Procurar tag `@Test` e clicar no icone de rodar teste

## Comandos úteis

### comandos GIT

* `git init` - Na pasta do projeto
* `git add .` - Para adicionar todos os arquivos
* `git add README.md` - Para adicionar o arquivo Readme.me
* `git commit -m "first commit"` - Criar comentário do commit
* `git config --global --edit` - Para editar o seu usuario e para configurar o repositório

### Vim
Comandos para Vi:
* `:q`  – Fecha o arquivo e encerra o Vim
* `:wq` – Salva as alterações, fecha o arquivo e encerra o Vim (junção dos comandos :w, que salva o arquivo, e :q para sair)
* `:q!` – Descarta as alterações, fecha o arquivo e encerra o Vim

### Criar chave ssh

Rodar comandos no terminal:
```sh
ssh-keygen -t ed25519 -C <email>
eval "$(ssh-agent -s)"
ssh-add --apple-use-keychain ~/.ssh/id_ed25519
pbcopy < ~/.ssh/id_ed25519.pub
```
Colar no GitHub

### Caso não funcione acesso por ssh

Caso não funcione o push opte por esse metodo: [Clique aqui](https://www.doaction.com.br/en/blog/como-corrigir-o-erro-support-for-password-authentication-was-removed-please-use-a-personal-access-token-instead)

* vá em `Settings` e depois `Developer Settings`
* Clique em `Personal access tokens` e a seguir em `Tokens (classic)`
* Generate new token:
* Dê um nome para seu token em Note e a seguir marque os checkboxes para habilitar as permissões
  - Gerar o token
* Agora, você deve copiar o seu token:
  - A última etapa é adicionar o token recém copiado ao endereço do seu repositório remoto e assim subir alterações para o github:
```
git remote set-url origin https://{{TOKEN}}@github.com/gguilhermesantos/Testes_Automatizados_API
git push -u origin master
```


<p align="center">(<a href="#readme-top">Voltar ao topo</a>)</p>
