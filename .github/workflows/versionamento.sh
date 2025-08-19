#!/bin/bash

# Obtendo o ano atual
ano_corrente=$(date +"%y")

ls -la
cd ..
ls -la
cd .. 
ls -la

# Nome do arquivo pom.xml
arquivo_pom="pom.xml"

# Verificar se o arquivo pom.xml existe
if [[ ! -f $arquivo_pom ]]; then
  echo "Arquivo $arquivo_pom não encontrado!"
  exit 1
fi

app_version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
echo ">>>VERSIONAMENTO SEMANTICO<<<"
echo "Ultima versao: $app_version"

# Separando os componentes da versão
IFS='.' read -r major minor patch <<< "$app_version"
# Tipo do pull request (recebido como argumento)
pr_branch=$1
echo "pr_branch: $pr_branch"

# Verificando se o PR é "fix/" ou "feature/"

if [[ $pr_branch == breaking-change/* ]]; then
    # Incremento do major    
    patch=$((major + 1))
elif [[ $pr_branch == feature/* ]]; then
    # Incremento do patch
    minor=$((minor + 1))
elif [[ $pr_branch == fix/* ]]; then
    # Incremento do minor    
    patch=$((patch + 1))
else
    echo "Tipo de branch não identificado. Use 'fix/', 'feature/' ou 'breaking-change/'."
    exit 1
fi

# Gerando a nova versão
nova_versao="${major}.${minor}.${patch}"
echo "Nova versão: $nova_versao"
mvn versions:set -DnewVersion=$nova_versao


