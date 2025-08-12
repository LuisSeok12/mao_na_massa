# Sistema de Gerenciamento de Funcionários em Java

Este projeto foi desenvolvido como parte de um teste técnico.  
O sistema gerencia informações de funcionários de uma indústria, utilizando **Java** com conceitos de **Programação Orientada a Objetos (POO)**, formatação de dados, agrupamentos e cálculos.

## Funcionalidades

- Cadastro de funcionários com nome, data de nascimento, salário e função.
- Remoção de funcionário específico da lista.
- Impressão de informações com:
  - Datas no formato `dd/MM/yyyy`
  - Valores numéricos formatados com separador de milhar (`.`) e vírgula para decimal.
- Aplicação de aumento percentual nos salários.
- Agrupamento de funcionários por função utilizando `Map`.
- Listagem de aniversariantes de meses específicos (outubro e dezembro).
- Identificação do funcionário mais velho.
- Ordenação alfabética de funcionários.
- Cálculo do total de salários.
- Cálculo de quantos salários mínimos cada funcionário recebe.

## Tecnologias utilizadas

- **Java 11+**
- **BigDecimal** para operações financeiras.
- **LocalDate** e **DateTimeFormatter** para manipulação de datas.
- **Collections Framework** (`List`, `Map`, `Comparator`, `Collectors`).


