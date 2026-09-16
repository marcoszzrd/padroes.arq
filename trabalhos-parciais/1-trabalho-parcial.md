# 1º Trabalho — Implementação das Entidades com JPA

## 🎯 Objetivo

A partir do **projeto-base disponibilizado pelo professor no GitHub**, você deverá complementar a implementação das entidades do sistema utilizando **Java, JPA e Lombok**.

Durante as aulas, as entidades **Categoria** e **Produto** foram utilizadas como exemplos de implementação.

Agora é sua vez de aplicar o mesmo conhecimento para completar as demais entidades do projeto.

O objetivo é praticar:

- criação e configuração de entidades JPA;
- definição de atributos;
- utilização de `@Entity`;
- definição de chave primária;
- geração automática de identificadores;
- utilização de `@ManyToOne`;
- utilização de `@OneToOne`;
- utilização do Lombok;
- relacionamento entre entidades;
- inserção de dados utilizando `import.sql`.

---

# 📂 1. Projeto-base

O professor disponibilizará um **projeto-base no GitHub** para cada turma.

Utilize **o repositório indicado pelo professor para a sua turma**.

O projeto já possui toda a estrutura necessária para a realização da atividade.

As entidades `Categoria` e `Produto` foram desenvolvidas como exemplos durante as aulas.

> ⚠️ **Não crie um novo projeto.**
>
> Utilize o projeto-base disponibilizado para sua turma e complete as entidades solicitadas neste trabalho.

---

# 🧩 2. Entidades que deverão ser implementadas

Você deverá completar a implementação das seguintes entidades:

- `Cliente`
- `Pedido`
- `ItemPedido`
- `Pagamento`

Você deverá completar o código existente, seguindo o padrão apresentado nas entidades `Categoria` e `Produto`.

> ⚠️ **Não altere ou remova a implementação das entidades `Categoria` e `Produto` utilizadas como exemplo.**

---

# 👤 3. Entidade Cliente

A entidade `Cliente` deverá possuir os seguintes atributos:

| Atributo | Tipo |
|---|---|
| `id` | `Integer` |
| `nome` | `String` |
| `email` | `String` |
| `telefone` | `String` |

A classe deverá ser configurada corretamente como uma entidade JPA e utilizar Lombok.

---

# 🛒 4. Entidade Pedido

A entidade `Pedido` deverá possuir os seguintes atributos:

| Atributo | Tipo |
|---|---|
| `id` | `Integer` |
| `data` | `LocalDateTime` |
| `status` | `String` |
| `valorTotal` | `BigDecimal` |
| `cliente` | `Cliente` |

### Relacionamentos

Um pedido:

- pertence a um cliente;
  

Os relacionamentos deverão ser configurados utilizando as anotações JPA apresentadas em aula.

---

# 📦 5. Entidade ItemPedido

A entidade `ItemPedido` deverá possuir os seguintes atributos:

| Atributo | Tipo |
|---|---|
| `id` | `Integer` |
| `quantidade` | `Integer` |
| `valorUnitario` | `BigDecimal` |
| `pedido` | `Pedido` |
| `produto` | `Produto` |

### Relacionamentos

Um item de pedido:

- pertence a um pedido;
- está relacionado a um produto.

Os relacionamentos deverão ser configurados utilizando as anotações JPA apresentadas em aula.

---

# 💳 6. Entidade Pagamento

A entidade `Pagamento` deverá possuir os seguintes atributos:

| Atributo | Tipo |
|---|---|
| `id` | `Integer` |
| `valor` | `BigDecimal` |
| `data` | `LocalDateTime` |
| `status` | `String` |
| `tipo` | `String` |
| `pedido` | `Pedido` |

### Relacionamento

Um pagamento está relacionado a um único pedido.

O relacionamento deverá ser configurado utilizando a anotação JPA apresentada em aula.

---

# 🔗 7. Relacionamentos

Os relacionamentos que deverão ser implementados são:

| Entidade de origem | Relacionamento | Entidade de destino |
|---|---|---|
| `Pedido` | `@ManyToOne` | `Cliente` |
| `ItemPedido` | `@ManyToOne` | `Pedido` |
| `ItemPedido` | `@ManyToOne` | `Produto` |
| `Pagamento` | `@OneToOne` | `Pedido` |

### Representação dos relacionamentos

```text
Cliente ─────────── N Pedido
                       │
                       │ N
                       ▼
                  ItemPedido
                       ▲
                       │ N
                       │
                    Produto


Pedido ─────────── 1 Pagamento
```

> 💡 Observe que um `ItemPedido` possui **dois relacionamentos independentes**:
>
> - um com `Pedido`;
> - outro com `Produto`.

---

# 🧠 8. Analisando os exemplos

As entidades `Categoria` e `Produto`, desenvolvidas durante as aulas, deverão ser utilizadas como **referência para a implementação**.

Observe como foram utilizados:

- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- Lombok
- `@ManyToOne`

A partir desses exemplos, implemente as demais entidades.

> 💡 **Não copie simplesmente uma classe.**
>
> Observe o que cada anotação faz e aplique o mesmo conceito nas novas entidades.

---

# 🛠️ 9. Utilização do Lombok

As entidades deverão utilizar **Lombok**, seguindo o padrão apresentado em aula.

Utilize as anotações necessárias para evitar a implementação manual de métodos como:

- getters;
- setters;
- construtores.

Siga o padrão utilizado nas entidades já implementadas no projeto.

---

# 🗃️ 10. Arquivo `import.sql`

Além da implementação das entidades, você deverá criar e configurar o arquivo:

```text
import.sql
```

Esse arquivo deverá conter dados para popular o banco de dados automaticamente.

## Quantidade de registros

Você deverá inserir **5 registros para cada entidade do sistema**.

O sistema possui 6 entidades:

| Entidade | Registros |
|---|---:|
| `Categoria` | 5 |
| `Produto` | 5 |
| `Cliente` | 5 |
| `Pedido` | 5 |
| `ItemPedido` | 5 |
| `Pagamento` | 5 |
| **Total** | **30** |

> ⚠️ **Atenção:** mesmo as entidades `Categoria` e `Produto`, que já foram implementadas em aula, deverão possuir **5 registros no `import.sql`**.

---

## 🔗 Dados relacionados

Os dados inseridos no `import.sql` deverão respeitar os relacionamentos entre as entidades.

Por exemplo:

- os produtos deverão estar associados a categorias existentes;
- os pedidos deverão estar associados a clientes existentes;
- os itens de pedido deverão estar associados a pedidos existentes;
- os itens de pedido deverão estar associados a produtos existentes;
- os pagamentos deverão estar associados a pedidos existentes.

Os registros deverão ser inseridos em uma ordem que permita que os relacionamentos sejam resolvidos corretamente.

### Ordem recomendada

```text
1. Categoria
2. Produto
3. Cliente
4. Pedido
5. ItemPedido
6. Pagamento
```

> 💡 Utilize dados fictícios e coerentes com o sistema.

---

# 🧪 11. Teste do projeto

Depois de implementar as entidades e criar o `import.sql`, execute o projeto e verifique se ele funciona corretamente.

Antes da entrega, certifique-se de que:

- o projeto compila;
- não existem erros nas entidades;
- as entidades estão sendo reconhecidas pelo JPA;
- os relacionamentos estão configurados corretamente;
- o `import.sql` não apresenta erros;
- os dados são inseridos corretamente;
- existem 5 registros para cada entidade.

---

# 💻 12. Desenvolvimento no GitHub Codespaces

O desenvolvimento deverá ser realizado no **GitHub Codespaces**, utilizando o projeto fornecido pelo professor.

Faça as alterações diretamente nos arquivos existentes do projeto.

Não crie um novo projeto.

Não remova as implementações de `Categoria` e `Produto`.

---

# 💾 13. Salvando o trabalho

Depois de concluir as alterações, utilize o **Controle de Código Fonte** disponível no GitHub Codespaces.

1. Abra o **Controle de Código Fonte**.
2. Confira os arquivos modificados.
3. Informe uma mensagem para o commit.

Exemplo:

```text
Implementa entidades e dados iniciais
```

4. Realize o **Commit**.
5. Utilize **Sincronizar Alterações / Sync Changes** para enviar as alterações ao GitHub.

> ⚠️ Certifique-se de que as alterações foram sincronizadas antes de realizar a entrega.

---

# 📤 14. Entrega

A atividade deverá ser realizada **individualmente**.

A entrega deverá ser feita por meio do **link do seu repositório no GitHub**.

O repositório deverá estar **público**, permitindo que o projeto possa ser acessado e avaliado.

### Informe:

```text
Nome:
RA:
Link do repositório:
```

> ⚠️ **Atenção:** envie o link do repositório utilizado para realizar o trabalho.

---

# ✅ 15. Checklist antes da entrega

## Cliente

- [ ] Possui `id` do tipo `Integer`
- [ ] Possui `nome`
- [ ] Possui `email`
- [ ] Possui `telefone`
- [ ] Está configurada como entidade JPA
- [ ] Utiliza Lombok

## Pedido

- [ ] Possui `id` do tipo `Integer`
- [ ] Possui `data`
- [ ] Possui `status`
- [ ] Possui `valorTotal`
- [ ] Possui relacionamento com `Cliente`
- [ ] Possui relacionamento com `Pagamento`
- [ ] Utiliza `@ManyToOne` para `Cliente`
- [ ] Utiliza `@OneToOne` para `Pagamento`
- [ ] Utiliza Lombok

## ItemPedido

- [ ] Possui `id` do tipo `Integer`
- [ ] Possui `quantidade`
- [ ] Possui `valorUnitario`
- [ ] Possui relacionamento com `Pedido`
- [ ] Possui relacionamento com `Produto`
- [ ] Utiliza `@ManyToOne` para `Pedido`
- [ ] Utiliza `@ManyToOne` para `Produto`
- [ ] Utiliza Lombok

## Pagamento

- [ ] Possui `id` do tipo `Integer`
- [ ] Possui `valor`
- [ ] Possui `data`
- [ ] Possui `status`
- [ ] Possui `tipo`
- [ ] Possui relacionamento com `Pedido`
- [ ] Utiliza `@OneToOne`
- [ ] Utiliza Lombok

## `import.sql`

- [ ] Criei o arquivo `import.sql`
- [ ] Possui 5 registros de `Categoria`
- [ ] Possui 5 registros de `Produto`
- [ ] Possui 5 registros de `Cliente`
- [ ] Possui 5 registros de `Pedido`
- [ ] Possui 5 registros de `ItemPedido`
- [ ] Possui 5 registros de `Pagamento`
- [ ] Totaliza 30 registros
- [ ] Os relacionamentos entre os registros estão corretos
- [ ] O `import.sql` é executado sem erros

## JPA

- [ ] Todas as entidades possuem `@Entity`
- [ ] Todas possuem chave primária
- [ ] Os IDs possuem geração automática
- [ ] Os relacionamentos estão configurados corretamente

## GitHub

- [ ] O projeto está no GitHub
- [ ] As alterações foram commitadas
- [ ] As alterações foram sincronizadas
- [ ] O repositório está público
- [ ] Enviei o link correto

---

# 🎯 16. Resultado esperado

Ao finalizar a atividade, o projeto deverá possuir as seis entidades:

```text
Categoria
    │
    │ 1:N
    ▼
Produto
    │
    │ 1:N
    ▼
ItemPedido ◄────────── N : 1 ────────── Pedido
                                            │
                                            │ 1:1
                                            ▼
                                        Pagamento
                                           
Pedido ────────── N : 1 ────────── Cliente
```

Os relacionamentos implementados deverão ser:

```text
Pedido → Cliente       @ManyToOne
ItemPedido → Pedido    @ManyToOne
ItemPedido → Produto   @ManyToOne
Pagamento → Pedido     @OneToOne
```

Além disso, o projeto deverá possuir um `import.sql` contendo:

**5 registros para cada uma das 6 entidades, totalizando 30 registros.**

---

## 💡 Dica

**Não tente fazer tudo de uma vez.**

Utilize as entidades `Categoria` e `Produto` como referência.

Analise:

- quais anotações foram utilizadas;
- onde elas foram colocadas;
- como os atributos foram declarados;
- como os relacionamentos foram configurados;
- como o Lombok foi utilizado.

Depois, aplique o mesmo raciocínio nas entidades que você precisa completar.

O objetivo é que você consiga **reproduzir o padrão apresentado em aula em novas situações**.
