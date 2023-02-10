# Testes_Automatizados_API

Git init na pasta do projeto
git add .
git add README.md
git commit -m "first commit"
* git config --global --edit para editar o seu usuario e para o repo (Não funcionou)

Para Vim
* :q – Fecha o arquivo e encerra o Vim
* :wq – Salva as alterações, fecha o arquivo e encerra o Vim (junção dos comandos :w, que salva o arquivo, e :q para sair)
* :q! – Descarta as alterações, fecha o arquivo e encerra o Vim

Criar uma chave ssh:
ssh-keygen -t ed25519 -C <email>
eval "$(ssh-agent -s)"
ssh-add --apple-use-keychain ~/.ssh/id_ed25519
pbcopy < ~/.ssh/id_ed25519.pub. ai a chave vai ta no ctrl+V 
Cola no GitHub

git remote add origin URL-GERADA-PELO-GIT
git push -u origin master

Como nao funcionou criar a chave
https://www.doaction.com.br/en/blog/como-corrigir-o-erro-support-for-password-authentication-was-removed-please-use-a-personal-access-token-instead

vá em Settings e depois Developer Settings
Clique em Personal access tokens e a seguir em Tokens (classic)
Generate new token:
Dê um nome para seu token em Note e a seguir marque os checkboxes para habilitar as permissões
Gerar o token
Agora, você deve copiar o seu token:
A última etapa é adicionar o token recém copiado ao endereço do seu repositório remoto:
git remote set-url origin https://{{TOKEN}}@github.com/{{gguilhermesantos}}/{{REPO}}