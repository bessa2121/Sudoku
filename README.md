# 🧩 Sudoku em Java (Console)

Projeto de um jogo de **Sudoku em Java**, executado no **console**, desenvolvido com foco em **orientação a objetos**, organização de código e boas práticas para aprendizado e portfólio.

---

## 📌 Objetivo do Projeto

Este projeto tem como objetivo praticar e consolidar conceitos fundamentais de Java, como:

- Programação Orientada a Objetos (POO)
- Encapsulamento e separação de responsabilidades
- Uso de `enum`
- Manipulação de coleções e `Streams`
- Estruturação de um jogo baseado em regras

---

## 🎮 Funcionalidades

- Exibição do tabuleiro de Sudoku no console
- Inserção de valores pelo jogador
- Validação de células fixas (não editáveis)
- Verificação do status do jogo:
  - Não iniciado
  - Incompleto
  - Completo
- Finalização automática ao completar corretamente o tabuleiro

---

## 🏗️ Estrutura do Projeto

```text
src/
 ├── application
 │   └── Main.java
 │
 ├── board
 │   ├── Board.java
 │   ├── BoardTemplate.java
 │   └── Space.java
 │
 └── enums
     └── GameStatusEnum.java
```
---
## 📂 Descrição das principais classes


| **Main** |
|----------|

- Responsável pela inicialização do jogo

- Interação com o usuário via console

- Controle do fluxo principal da aplicação

| **Board** |
|-----------|

- Representa o tabuleiro do Sudoku

- Contém a lógica principal do jogo

- Controla o estado das células e o progresso do jogo

| **Space** |
|-----------|

- Representa uma célula individual do tabuleiro

- Armazena:

  - Valor atual

  - Valor correto

  - Se a célula é fixa ou editável

| **BoardTemplate** |
|-------------------|

- Responsável apenas pela exibição visual do tabuleiro no console

- Separado da lógica do jogo para facilitar manutenção e evolução

| **GameStatusEnum** |
|--------------------|

- Enum que define os estados do jogo:
 
  - `NON_STARTED`

  - `INCOMPLETE`

  - `COMPLETE` 

---

## 🧠 Conceitos Aplicados

- Orientação a Objetos

- Enum para controle de estados

- Streams e operações funcionais

- Separação de responsabilidades

- Código legível e organizado

---

## ▶️ Como Executar

**1.** Clone o repositório:

```bash
git clone https://github.com/seu-usuario/sudoku.git
```
**2.** Abra o projeto em sua IDE (IntelliJ, Eclipse, VS Code, etc.)

**3.** Execute a classe:

```text
Main.java
```
**4.** Siga as instruções exibidas no console.

---

## 🚀 Possíveis Melhorias Futuras

- Validação de regras do Sudoku (linha, coluna e bloco 3x3)

- Melhor tratamento de entradas inválidas do usuário

- Separação completa entre lógica do jogo e controle (GameController)

- Implementação de testes unitários

- Interface gráfica (JavaFX ou front-end web futuramente)


---

## 📚 Aprendizados

**Este projeto contribuiu para o desenvolvimento de habilidades importantes, como:**

- Modelagem de problemas reais em Java

- Organização de código para projetos maiores

- Pensamento em evolução e manutenção de software

---

### 👤 Autor

**Desenvolvido por Davi Tavares**
