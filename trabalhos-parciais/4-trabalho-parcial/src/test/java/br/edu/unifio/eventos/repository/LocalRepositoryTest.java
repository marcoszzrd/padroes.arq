package br.edu.unifio.eventos.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.unifio.eventos.model.Local;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class LocalRepositoryTest {

    @Autowired
    private LocalRepository repository;

    @Autowired
    private TestEntityManager em;

    private Local criarLocal(String nome, String cidade, int capacidade) {
        Local l = new Local();
        l.setNome(nome);
        l.setCidade(cidade);
        l.setCapacidade(capacidade);
        return repository.save(l);
    }

    @Test
    void deveInserirLocal() {
        Local local = new Local();
        local.setNome("Auditorio Central");
        local.setCidade("Ourinhos");
        local.setCapacidade(200);

        Local salvo = repository.save(local);

        assertNotNull(salvo.getId());
        assertEquals("Auditorio Central", salvo.getNome());
        assertEquals(1, repository.count());
    }

    @Test
    void deveBuscarLocalPorId() {
        Long id = criarLocal("Ginasio", "Ourinhos", 500).getId();
        em.flush();
        em.clear();

        Local encontrado = repository.findById(id).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Ginasio", encontrado.getNome());
        assertEquals("Ourinhos", encontrado.getCidade());
        assertEquals(500, encontrado.getCapacidade());
    }

    @Test
    void deveListarLocais() {
        criarLocal("Auditorio", "Ourinhos", 200);
        criarLocal("Ginasio", "Marilia", 500);

        List<Local> lista = repository.findAll();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(l -> l.getNome().equals("Auditorio")));
        assertTrue(lista.stream().anyMatch(l -> l.getNome().equals("Ginasio")));
    }

    @Test
    void deveAlterarLocal() {
        Local local = criarLocal("Auditorio", "Ourinhos", 200);
        Long id = local.getId();

        local.setCapacidade(350);
        repository.saveAndFlush(local);
        em.clear();

        Local alterado = repository.findById(id).orElseThrow();
        assertEquals(350, alterado.getCapacidade());
        assertEquals(1, repository.count());
    }

    @Test
    void deveExcluirLocal() {
        Long id = criarLocal("Auditorio", "Ourinhos", 200).getId();
        assertTrue(repository.existsById(id));

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }
}
