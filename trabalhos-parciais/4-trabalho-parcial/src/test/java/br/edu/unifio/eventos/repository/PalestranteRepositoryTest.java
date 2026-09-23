package br.edu.unifio.eventos.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.unifio.eventos.model.Palestrante;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class PalestranteRepositoryTest {

    @Autowired
    private PalestranteRepository repository;

    @Autowired
    private TestEntityManager em;

    private Palestrante criarPalestrante(String nome, String email) {
        Palestrante p = new Palestrante();
        p.setNome(nome);
        p.setEmail(email);
        return repository.save(p);
    }

    @Test
    void deveInserirPalestrante() {
        Palestrante obj = new Palestrante();
        obj.setNome("Maria Silva");
        obj.setEmail("maria@email.com");

        Palestrante salvo = repository.save(obj);

        assertNotNull(salvo.getId());
        assertEquals("Maria Silva", salvo.getNome());
        assertEquals(1, repository.count());
    }

    @Test
    void deveBuscarPalestrantePorId() {
        Long id = criarPalestrante("Joao Souza", "joao@email.com").getId();
        em.flush();
        em.clear();

        Palestrante encontrado = repository.findById(id).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Joao Souza", encontrado.getNome());
        assertEquals("joao@email.com", encontrado.getEmail());
    }

    @Test
    void deveListarPalestrantes() {
        criarPalestrante("Maria Silva", "maria@email.com");
        criarPalestrante("Joao Souza", "joao@email.com");

        List<Palestrante> lista = repository.findAll();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(p -> p.getNome().equals("Maria Silva")));
        assertTrue(lista.stream().anyMatch(p -> p.getNome().equals("Joao Souza")));
    }

    @Test
    void deveAlterarPalestrante() {
        Palestrante obj = criarPalestrante("Maria Silva", "maria@email.com");
        Long id = obj.getId();

        obj.setEmail("maria.nova@email.com");
        repository.saveAndFlush(obj);
        em.clear();

        Palestrante alterado = repository.findById(id).orElseThrow();
        assertEquals("maria.nova@email.com", alterado.getEmail());
        assertEquals(1, repository.count());
    }

    @Test
    void deveExcluirPalestrante() {
        Long id = criarPalestrante("Maria Silva", "maria@email.com").getId();
        assertTrue(repository.existsById(id));

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }
}
