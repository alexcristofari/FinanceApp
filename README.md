# FinanceApp

FinanceApp é um aplicativo Android desenvolvido para auxiliar os usuários no gerenciamento de suas finanças pessoais. Com uma interface intuitiva e funcionalidades essenciais, o aplicativo permite que os usuários acompanhem receitas, despesas, metas financeiras e visualizem gráficos de despesas por categoria.

## 📖 Índice

- [Capturas de Tela](#-capturas-de-tela)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Configuração e Instalação](#-configuração-e-instalação)
- [Como Usar](#-como-usar)
- [Banco de Dados](#-banco-de-dados)
- [Desafios Enfrentados](#-desafios-enfrentados)
- [Possíveis Melhorias Futuras](#-possíveis-melhorias-futuras)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)
- [Contato](#-contato)
- [Agradecimentos](#-agradecimentos)

## 📱 Capturas de Tela

<!-- Adicione aqui imagens do aplicativo para ilustrar as telas principais -->
<!-- Exemplo: -->
<p align="center">
  <img src="screenshots/login.png" alt="Tela de Login" width="200">
  <img src="screenshots/main.png" alt="Tela Principal" width="200">
  <img src="screenshots/adicionar_transacao.png" alt="Adicionar Transação" width="200">
  <img src="screenshots/grafico_pizza.png" alt="Gráfico de Pizza" width="200">
</p>

## 📝 Funcionalidades

- **Autenticação de Usuário:**
  - Registro de novos usuários com nome, e-mail e senha.
  - Login de usuários existentes.
  - Manutenção da sessão do usuário utilizando SharedPreferences.

- **Gestão de Transações:**
  - Adicionar novas transações financeiras (receitas e despesas).
  - Editar e excluir transações existentes.
  - Classificar transações por tipo, categoria e data.
  - Adicionar descrições para transações.

- **Definição de Metas Financeiras:**
  - Definir uma meta financeira pessoal.
  - Acompanhar o progresso em relação à meta estabelecida.
  - Armazenamento da meta utilizando SharedPreferences.

- **Listagem de Transações:**
  - Visualizar todas as transações registradas em uma lista organizada.
  - Acessar detalhes de cada transação.
  - Navegação intuitiva entre as telas.

- **Visualização Gráfica:**
  - Exibir um gráfico de pizza das despesas por categoria.
  - Análise visual dos hábitos de gastos.

- **Exportação para PDF:**
  - Gerar um arquivo PDF com todas as transações registradas.
  - Compartilhar ou armazenar o relatório financeiro.

- **Navegação Intuitiva:**
  - Botão "Voltar" nas atividades secundárias para melhorar a usabilidade.
  - Fluxo de navegação consistente entre as telas.

## 🛠️ Tecnologias Utilizadas

- **Linguagem de Programação:** Kotlin
- **Plataforma:** Android SDK
- **Banco de Dados Local:** SQLite
- **Interface do Usuário:** XML Layouts, Views nativas do Android
- **Bibliotecas Externas:**
  - [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) para gráficos
- **Persistência de Dados Simples:** SharedPreferences
- **Gerenciamento de Dependências:** Gradle com Kotlin DSL

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura organizada para facilitar a manutenção e escalabilidade.

- **MVC (Model-View-Controller):**
  - **Modelos (Models):** Representam os dados do aplicativo, como `Usuario` e `Transacao`.
  - **Visualizações (Views):** Layouts XML que definem a interface do usuário.
  - **Controladores (Controllers):** Atividades que contêm a lógica de negócio e interações do usuário.

- **Pacotes:**
  - `activities`: Contém todas as atividades do aplicativo.
  - `adapters`: Adaptadores personalizados para listas.
  - `models`: Classes que representam os modelos de dados.
  - `database`: Classe `DatabaseHelper` para gerenciamento do banco de dados.

## 💻 Configuração e Instalação

### Pré-requisitos

- **Android Studio Flamingo** ou superior.
- **JDK 8** ou superior.
- **Gradle 8.0** ou superior.
- Dispositivo Android ou emulador com **API Level 21** ou superior.

### Passos para Configuração

1. **Clonando o Repositório:**

   ```bash
   git clone https://github.com/seu-usuario/FinanceApp.git
