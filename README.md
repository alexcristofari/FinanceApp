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

  ### Abrindo o Projeto no Android Studio:

- Abra o **Android Studio**.
- Selecione **"Open an Existing Project"**.
- Navegue até o diretório do projeto clonado e selecione-o.
- Aguarde o Android Studio sincronizar o projeto com o Gradle.

### Sincronizando o Gradle:

- Caso ocorram erros, verifique se as versões do Gradle e dos plugins estão compatíveis.
- Certifique-se de que os repositórios estão definidos corretamente no `settings.gradle.kts`.

### Configurando o Emulador ou Dispositivo:

- Conecte um dispositivo físico com **depuração USB** ativada ou configure um emulador Android com a API adequada.

## 🚀 Como Usar

1. **Executando o Aplicativo:**

   - No Android Studio, clique em **"Run"** ou pressione **Shift + F10**.
   - Selecione o dispositivo ou emulador onde deseja executar o aplicativo.

2. **Registrando um Novo Usuário:**

   - Na tela de login, clique em **"Registrar"**.
   - Preencha as informações solicitadas: nome, e-mail e senha.
   - Após o registro, você será redirecionado para a tela principal.

3. **Adicionando Transações:**

   - Na tela principal, clique em **"Adicionar Transação"**.
   - Preencha os detalhes da transação e salve.
   - A transação será adicionada à sua lista de transações.

4. **Visualizando Transações:**

   - Na tela principal, clique em **"Ver Transações"**.
   - Uma lista com todas as transações registradas será exibida.
   - Clique em uma transação para editar ou excluir.

5. **Definindo Metas Financeiras:**

   - Na tela principal, clique em **"Definir Meta"**.
   - Insira o valor da meta e salve.
   - O progresso em relação à meta será acompanhado.

6. **Visualizando Gráficos:**

   - Na tela principal, clique em **"Ver Gráfico de Despesas"**.
   - O gráfico de pizza exibirá as despesas por categoria.

7. **Exportando Transações para PDF:**

   - Na tela principal, clique em **"Exportar para PDF"**.
   - O aplicativo solicitará permissão para acessar o armazenamento.
   - Um arquivo PDF será gerado com suas transações.

## 🗄️ Banco de Dados

O aplicativo utiliza o **SQLite** para armazenamento local dos dados.

- **Tabelas:**
  - **Usuário (`usuario`):** Armazena informações dos usuários registrados.
  - **Transação (`transacao`):** Armazena as transações financeiras dos usuários.

- **Relação:**
  - **Um-para-Muitos:** Um usuário pode ter várias transações, mas cada transação pertence a um único usuário.

- **Implementação:**
  - A classe `DatabaseHelper` gerencia a criação e atualização do banco de dados.
  - Operações CRUD são implementadas para manipular os dados.

## 🐛 Desafios Enfrentados

### 1. **Erro no Login que Fechava o Aplicativo**

- **Problema:**
  - O aplicativo fechava inesperadamente ao tentar fazer login.

- **Solução:**
  - Implementação de tratamento adequado para cursores vazios ou nulos.
  - Validação dos campos de entrada e mensagens de erro amigáveis.
  - Verificação das consultas SQL e inicialização correta das variáveis.

### 2. **Dificuldade na Implementação do Gráfico de Pizza**

- **Problema:**
  - Integração da biblioteca `MPAndroidChart` apresentou erros de configuração.

- **Solução:**
  - Ajuste das configurações do Gradle utilizando a sintaxe do Kotlin DSL.
  - Adição correta dos repositórios e dependências no `settings.gradle.kts` e `build.gradle.kts`.
  - Estudo da documentação da biblioteca e implementação cuidadosa do gráfico.

## 🌟 Possíveis Melhorias Futuras

- **Segurança Aprimorada:**
  - Implementação de hashing para armazenamento seguro de senhas.
  - Utilização de criptografia para dados sensíveis.

- **Interface do Usuário:**
  - Atualização do design para seguir as diretrizes do Material Design.
  - Implementação de temas claros e escuros.

- **Funcionalidades Adicionais:**
  - Sincronização de dados com a nuvem para backup e acesso multiplataforma.
  - Notificações personalizadas para lembretes financeiros.
  - Integração com APIs de bancos para importação automática de transações.

- **Internacionalização:**
  - Suporte a múltiplos idiomas.
  - Adaptação de formatos de data, hora e moeda para diferentes regiões.

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma **issue** ou enviar um **pull request**.

### Como Contribuir

1. Faça um **fork** do projeto.
2. Crie uma **branch** para sua feature (`git checkout -b feature/nova-feature`).
3. Faça **commit** das suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Faça o **push** para a branch (`git push origin feature/nova-feature`).
5. Abra um **Pull Request**.

## 📄 Licença

Este projeto está licenciado sob a **Licença MIT** - veja o arquivo [LICENSE](LICENSE) para detalhes.


## 🙏 Agradecimentos

Agradecemos a todos que contribuíram para este projeto e às seguintes bibliotecas de código aberto:

- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- Comunidade do Stack Overflow e fóruns de programação.
