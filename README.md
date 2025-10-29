
# FX - API de Câmbio

A FX é uma API de câmbio **SEM FINS COMERCIAIS** que utiliza o serviço da Wise para fornecer taxas de câmbio atualizadas.

[![Cobertura de Testes](https://img.shields.io/codecov/c/github/ungaratto93/fx)](https://codecov.io/gh/ungaratto93/fx)
## Negocio

O câmbio é a taxa de conversão entre duas moedas, indicando quanto de uma moeda é necessário para adquirir uma unidade da outra moeda. Se a taxa de câmbio para trocar 1 USD por reais é R$4,99, você pode multiplicar a quantidade de dólares desejada pela taxa para obter o valor equivalente em reais.

```bash
Exemplo:
Quantidade desejada em USD: $100
Taxa de câmbio: (USD/BRL) R$4,99
Cálculo: $100 * R$4,99 = R$499,00 
```
Portanto, ao trocar 100 dólares americanos a uma taxa de câmbio de R$4,99 por dólar, você obteria R$499,00.


## Arquitetura

Este projeto adota os princípios da **Arquitetura Limpa**, proposta por Robert C. Martin. A Arquitetura Limpa é uma abordagem de desenvolvimento de software que visa criar sistemas sustentáveis, flexíveis e testáveis.

![design solucao](https://github.com/lue93/fx/blob/main/src/main/resources/Captura%20de%20tela%202025-07-10%20203059.png?raw=true)

### Filosofia da Arquitetura Limpa

A Arquitetura Limpa preconiza a organização do código em camadas, de forma que cada camada tenha uma responsabilidade clara e bem definida. As principais camadas são:

1. **Entidades**: Representa as entidades de negócios, contendo apenas dados e métodos relacionados a esses dados.

2. **Casos de Uso**: Responsável por orquestrar a lógica de negócios, conectando as entidades e coordenando as operações.

3. **Adaptadores (Mappers)**: Converte dados entre a forma bruta em que são armazenados nas camadas internas e a forma como são apresentados externamente.

4. **Frameworks e Drivers**: Camada externa que contém os detalhes de implementação específicos do framework, como banco de dados, interfaces com o usuário, etc.

### Benefícios da Arquitetura Limpa

- **Manutenibilidade**: O código é organizado e modular, facilitando a manutenção e evolução do sistema.

- **Testabilidade**: A separação de responsabilidades torna mais fácil escrever testes automatizados para cada camada.

- **Indep!endência de Frameworks**: A lógica de negócios não é acoplada a frameworks específicos, tornando possível alterar componentes externos sem afetar o núcleo do sistema.

### Estrutura do Projeto

A estrutura do projeto segue a divisão proposta pela Arquitetura Limpa, promovendo uma base sólida para o desenvolvimento de software de qualidade.

- **`src/main/java/br/com/ungaratto93/fx/domain`**: Contém as entidades e regras de negócios.

- **`src/main/java/br/com/ungaratto93/fx/usecase`**: Implementa os casos de uso que coordenam as operações entre as entidades.

- **`src/main/java/br/com/ungaratto93/fx/infra`**: Adapta as interfaces externas, como por exemplo, serviços de terceiros, banco de dados e etc.

- **`src/main/java/br/com/ungaratto93/fx/ui`**: Apresenta os dados ao usuário e recebe as interações.

Este projeto busca seguir os princípios da Arquitetura Limpa para fornecer um código flexível e fácil de manter.



## Tecnologia Utilizada

O projeto FX utiliza as seguintes tecnologias e ferramentas:

- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.2.1**: Framework de desenvolvimento de aplicações Java.
- **Spring Cloud OpenFeign**: Integração com serviços da Wise usando Feign.
- **Spring Cloud DevTools**: Ferramenta de desenvolvimento para agilizar o ciclo de desenvolvimento.
- **Project Lombok**: Biblioteca para simplificar o código Java, eliminando a necessidade de escrever código boilerplate.
- **JUnit 4.13.2**: Framework de testes unitários para Java.
- **Springdoc OpenAPI**: Geração automática de documentação OpenAPI para APIs Spring Boot.
- **Jacoco Maven Plugin 0.8.11**: Plugin para análise de cobertura de código.
- **Spring Cloud Dependencies 2023.0.0**: Gerenciamento de dependências para projetos Spring Cloud.


## Instalação

Certifique-se de ter o Java e o Maven instalados em sua máquina antes de prosseguir.

```bash
# Clone o repositório
git clone https://github.com/ungaratto93/fx.git

# Acesse o diretório do projeto
cd fx

# Execute a aplicação
mvn spring-boot:run
```

## Como Usar
### Obter Taxa de Câmbio
Endpoint: /fx/v1/exchange

Envie uma solicitação POST com o seguinte corpo:
```bash
{
  "from": "USD",
  "to": "BRL",
  "amount": 2.00
}
```
Exemplo de resposta
```bash
{
  "fiat": 9.00
}
```


## Documentacao

* http://localhost:8080/swagger-ui/index.html

## Docker
Comando para executar o projeto via imagem docker
```bash
docker pull ungaratto93/api-fx:latest
```
