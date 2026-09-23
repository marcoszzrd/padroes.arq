package br.edu.unifio.eventos.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.unifio.eventos.model.Categoria;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// @DataJpaTest sobe um banco H2 em memoria e desfaz (rollback) cada teste no final.
@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    // Usado para limpar o cache (em.clear) e forcar uma nova consulta real ao banco
    @Autowired
    private TestEntityManager em;

    private Categoria criarCategoria(String nome, String descricao) {
        Categoria c = new Categoria();
        c.setNome(nome);
        c.setDescricao(descricao);
        return repository.save(c);
    }

    @Test
    void deveInserirCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Tecnologia");
        categoria.setDescricao("Eventos de tecnologia");

        Categoria salva = repository.save(categoria);

        assertNotNull(salva.getId());
        assertEquals("Tecnologia", salva.getNome());
        assertEquals(1, repository.count());
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Long id = criarCategoria("Saude", "Eventos de saude").getId();
        em.flush();
        em.clear();

        Categoria encontrada = repository.findById(id).orElse(null);

        assertNotNull(encontrada);
        assertEquals(id, encontrada.getId());
        assertEquals("Saude", encontrada.getNome());
        assertEquals("Eventos de saude", encontrada.getDescricao());
    }

    @Test
    void deveListarCategorias() {
        criarCategoria("Tecnologia", "Tech");
        criarCategoria("Educacao", "Edu");

        List<Categoria> lista = repository.findAll();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(c -> c.getNome().equals("Tecnologia")));
        assertTrue(lista.stream().anyMatch(c -> c.getNome().equals("Educacao")));
    }

    @Test
    void deveAlterarCategoria() {
        Categoria categoria = criarCategoria("Tecnologia", "Tech");
        Long id = categoria.getId();

        categoria.setNome("Tecnologia e Inovacao");
        repository.saveAndFlush(categoria);
        em.clear(); // esquece o objeto em memoria: a proxima busca vai ao banco

        Categoria alterada = repository.findById(id).orElseThrow();
        assertEquals("Tecnologia e Inovacao", alterada.getNome());
        assertEquals(1, repository.count()); // alterou, nao criou um novo
    }

    @Test
    void deveExcluirCategoria() {
        Long id = criarCategoria("Tecnologia", "Tech").getId();
        assertTrue(repository.existsById(id));

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }
}
