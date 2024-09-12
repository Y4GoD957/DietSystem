# Sistema de Saúde Alimentar (Diet System)

Este projeto é um sistema completo que ajuda os usuários a manter uma alimentação saudável, gerenciar suas dietas e recomendar exercícios personalizados. Ele calcula métricas importantes como IMC, TMB, gasto calórico e peso ideal, gerando planos alimentares e de exercícios com base nos dados individuais dos usuários.

## Funcionalidades Principais

- **Cálculos de Métricas Corporais:**
  - **IMC (Índice de Massa Corporal)**: Calcula e exibe o IMC do usuário com base no peso e altura.
  - **TMB (Taxa de Metabolismo Basal)**: Calcula a quantidade de calorias que o corpo gasta em repouso.
  - **Gasto Calórico**: Calcula a quantidade de calorias necessárias para manter o peso atual com base no nível de atividade física.
  - **Peso Ideal**: Fornece uma estimativa do peso ideal do usuário com base em fórmulas de saúde.

- **Montagem de Dietas Personalizadas:**
  - Gera planos alimentares personalizados de acordo com as métricas e objetivos do usuário (perder peso, ganhar massa muscular, manter o peso).
  - Exibe os detalhes das refeições, calorias recomendadas e nutrientes principais.

- **Recomendações de Exercícios Personalizados:**
  - Cria planos de exercícios de acordo com o objetivo do usuário.
  - Exibe detalhes sobre a frequência, tipos de exercícios e duração recomendada.

## Tecnologias Utilizadas

- **Frontend**: Vue.js
- **Backend**: Spring Boot (Java)
- **Banco de Dados**: MySQL
- **Estilização**: CSS com foco em responsividade e design acessível
- **Outras Dependências**: 
  - Axios para requisições HTTP.
  - SweetAlert2 para alertas e notificações interativas.

## Estrutura do Projeto

- **Perfil do Usuário**: Cada usuário pode visualizar e atualizar suas informações, além de acessar dietas e planos de exercícios personalizados.
- **Sistema de Abas**: O sistema de navegação por abas permite alternar entre diferentes seções, como Perfil, Dieta, Exercícios e Usuários (apenas para administradores).
- **Administração**: Administradores podem gerenciar os usuários do sistema, adicionar novos, editar informações e visualizar o progresso de cada um.

## Configuração e Execução do Projeto

### Requisitos

- **Node.js** (para o frontend)
- **Java 11** ou superior (para o backend)
- **MySQL** (para o banco de dados)

### Instruções para Rodar

#### Backend (Spring Boot)

1. Clone o repositório:
   ```bash
   git clone https://gitlab.com/Y4GoD957/DietSystem.git