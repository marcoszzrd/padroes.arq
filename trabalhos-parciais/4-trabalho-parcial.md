# Atividade – Testes de Repositórios com Spring Data JPA

Utilizando como base o projeto desenvolvido no **2º Trabalho Parcial**, implemente e teste a camada de persistência da aplicação utilizando **Spring Data JPA**.

## 1. Repositórios

Crie um `Repository` para cada entidade do projeto, utilizando as interfaces disponibilizadas pelo Spring Data JPA.

Os repositórios devem permitir, no mínimo, as operações necessárias para:

* inserir registros;
* consultar um registro pelo ID;
* listar registros;
* alterar registros;
* excluir registros.

## 2. Classes de teste

Para cada `Repository` criado, desenvolva uma classe de testes utilizando **JUnit**.

Cada classe de teste deverá possuir testes para as seguintes operações:

### Inserção

Crie um teste que:

* crie um novo objeto da entidade;
* preencha seus atributos;
* estabeleça os relacionamentos necessários com outras entidades;
* persista o objeto no banco de dados;
* utilize `assert` para verificar se a operação foi realizada corretamente.

### Busca por ID

Crie um teste que:

* insira um registro;
* realize a busca utilizando seu ID;
* verifique se o registro encontrado corresponde ao registro inserido;
* valide pelo menos dois atributos do objeto retornado.

### Listagem

Crie um teste que:

* insira pelo menos dois registros;
* utilize o método de listagem do Repository;
* verifique se os registros foram retornados;
* utilize `assert` para validar a quantidade de registros e/ou seus dados.

### Alteração

Crie um teste que:

* insira um registro;
* altere pelo menos um de seus atributos;
* persista a alteração;
* realize uma nova consulta ao banco;
* utilize `assert` para comprovar que o valor foi realmente alterado.

> **Importante:** a alteração deve modificar um registro existente e não criar um novo registro.

### Exclusão

Crie um teste que:

* insira um registro;
* confirme, utilizando um `assert`, que o registro existe;
* exclua o registro utilizando seu ID;
* confirme, utilizando um `assert`, que o registro não existe mais.

## 3. Testes envolvendo relacionamentos

Para as entidades que possuem relacionamento com outras entidades, os testes deverão considerar esses relacionamentos.

Por exemplo, caso `Produto` pertença a uma `Categoria`, o teste de inserção de um produto deverá associá-lo a uma categoria existente.

## 4. Qualidade dos testes

Os testes não devem apenas executar os métodos do `Repository`.

Cada teste deverá possuir **asserções (`assert`) que comprovem o resultado esperado da operação**.

Não serão considerados suficientes testes que apenas executem:

```java
repository.save(objeto);
repository.deleteById(id);
```

sem verificar o resultado da operação.

## 5. Requisitos mínimos

Para **cada entidade**, deverão existir:

| Operação     | Teste obrigatório |
| ------------ | ----------------- |
| Inserção     | 1 teste           |
| Busca por ID | 1 teste           |
| Listagem     | 1 teste           |
| Alteração    | 1 teste           |
| Exclusão     | 1 teste           |

Portanto, cada entidade deverá possuir **no mínimo 5 testes**.

## 6. Entrega

A entrega deverá ser realizada através do **link do repositório GitHub contendo o projeto completo**.

O projeto deverá:

* estar compilando corretamente;
* possuir todos os `Repositories`;
* possuir todas as classes de teste;
* possuir os testes implementados e executando corretamente;
* conter os `asserts` necessários para validar os resultados;
* possuir os commits realizados durante o desenvolvimento.

### Observação

O objetivo da atividade não é apenas implementar um CRUD, mas **demonstrar, por meio de testes automatizados, que as operações realizadas pelos `Repositories` estão funcionando corretamente e que os dados estão sendo efetivamente persistidos no banco de dados**.
