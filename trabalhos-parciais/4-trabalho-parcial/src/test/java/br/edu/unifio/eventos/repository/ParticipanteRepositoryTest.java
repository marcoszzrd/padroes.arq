package br.edu.unifio.eventos.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.unifio.eventos.model.Participante;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ParticipanteRepositoryTest {

    @Autowired
    private ParticipanteRepository repository;

    @Autowired
    private TestEntityManager em;

    private Participante criarParticipante(String nome, String email) {
        Participante p = new Participante();
        p.setNome(nome);
        p.setEmail(email);
        return repository.save(p);
    }

    @Test
    void deveInserirParticipante() {
        Participante obj = new Participante();
        obj.setNome("Maria Silva");
        obj.setEmail("maria@email.com");

        Participante salvo = repository.save(obj);

        assertNotNull(salvo.getId());
        assertEquals("Maria Silva", salvo.getNome());
        assertEquals(1, repository.count());
    }

    @Test
    void deveBuscarParticipantePorId() {
        Long id = criarParticipante("Joao Souza", "joao@email.com").getId();
        em.flush();
        em.clear();

        Participante encontrado = repository.findById(id).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Joao Souza", encontrado.getNome());
        assertEquals("joao@email.com", encontrado.getEmail());
    }

    @Test
    void deveListarParticipantes() {
        criarParticipante("Maria Silva", "maria@email.com");
        criarParticipante("Joao Souza", "joao@email.com");

        List<Participante> lista = repository.findAll();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(p -> p.getNome().equals("Maria Silva")));
        assertTrue(lista.stream().anyMatch(p -> p.getNome().equals("Joao Souza")));
    }

    @Test
    void deveAlterarParticipante() {
        Participante obj = criarParticipante("Maria Silva", "maria@email.com");
        Long id = obj.getId();

        obj.setEmail("maria.nova@email.com");
        repository.saveAndFlush(obj);
        em.clear();

        Participante alterado = repository.findById(id).orElseThrow();
        assertEquals("maria.nova@email.com", alterado.getEmail());
        assertEquals(1, repository.count());
    }

    @Test
    void deveExcluirParticipante() {
        Long id = criarParticipante("Maria Silva", "maria@email.com").getId();
        assertTrue(repository.existsById(id));

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }
}
