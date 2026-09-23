package br.edu.unifio.eventos.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.unifio.eventos.model.Categoria;
import br.edu.unifio.eventos.model.Evento;
import br.edu.unifio.eventos.model.Inscricao;
import br.edu.unifio.eventos.model.Local;
import br.edu.unifio.eventos.model.Participante;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class InscricaoRepositoryTest {

    @Autowired
    private InscricaoRepository repository;
    @Autowired
    private ParticipanteRepository participanteRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private LocalRepository localRepository;
    @Autowired
    private TestEntityManager em;

    // ---- Metodos auxiliares: criam os registros dos quais a Inscricao depende ----
    private Participante criarParticipante(String nome, String email) {
        Participante p = new Participante();
        p.setNome(nome);
        p.setEmail(email);
        return participanteRepository.save(p);
    }

    private Evento criarEvento() {
        Categoria categoria = new Categoria();
        categoria.setNome("Tecnologia");
        categoria.setDescricao("Eventos de tecnologia");
        categoriaRepository.save(categoria);

        Local local = new Local();
        local.setNome("Auditorio Central");
        local.setCidade("Ourinhos");
        local.setCapacidade(200);
        localRepository.save(local);

        Evento evento = new Evento();
        evento.setTitulo("Semana de Tecnologia");
        evento.setDataEvento(LocalDate.of(2026, 11, 10));
        evento.setCategoria(categoria);
        evento.setLocal(local);
        return eventoRepository.save(evento);
    }

    private Inscricao criarInscricao(Participante participante, Evento evento) {
        Inscricao i = new Inscricao();
        i.setDataInscricao(LocalDate.of(2026, 10, 1));
        i.setParticipante(participante);
        i.setEvento(evento);
        return repository.save(i);
    }

    @Test
    void deveInserirInscricao() {
        Participante participante = criarParticipante("Joao Souza", "joao@email.com");
        Evento evento = criarEvento();

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(LocalDate.of(2026, 10, 1));
        inscricao.setParticipante(participante); // relacionamento com Participante
        inscricao.setEvento(evento);             // relacionamento com Evento

        Inscricao salva = repository.save(inscricao);

        assertNotNull(salva.getId());
        assertEquals(participante.getId(), salva.getParticipante().getId());
        assertEquals(evento.getId(), salva.getEvento().getId());
        assertEquals(1, repository.count());
    }

    @Test
    void deveBuscarInscricaoPorId() {
        Long id = criarInscricao(criarParticipante("Joao Souza", "joao@email.com"), criarEvento()).getId();
        em.flush();
        em.clear();

        Inscricao encontrada = repository.findById(id).orElse(null);

        assertNotNull(encontrada);
        assertEquals(LocalDate.of(2026, 10, 1), encontrada.getDataInscricao());
        assertEquals("Joao Souza", encontrada.getParticipante().getNome());
        assertEquals("Semana de Tecnologia", encontrada.getEvento().getTitulo());
    }

    @Test
    void deveListarInscricoes() {
        Evento evento = criarEvento();
        criarInscricao(criarParticipante("Joao Souza", "joao@email.com"), evento);
        criarInscricao(criarParticipante("Ana Lima", "ana@email.com"), evento);

        List<Inscricao> lista = repository.findAll();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(i -> i.getParticipante().getNome().equals("Joao Souza")));
        assertTrue(lista.stream().anyMatch(i -> i.getParticipante().getNome().equals("Ana Lima")));
    }

    @Test
    void deveAlterarInscricao() {
        Inscricao inscricao = criarInscricao(criarParticipante("Joao Souza", "joao@email.com"), criarEvento());
        Long id = inscricao.getId();

        inscricao.setDataInscricao(LocalDate.of(2026, 10, 15));
        repository.saveAndFlush(inscricao);
        em.clear();

        Inscricao alterada = repository.findById(id).orElseThrow();
        assertEquals(LocalDate.of(2026, 10, 15), alterada.getDataInscricao());
        assertEquals(1, repository.count());
    }

    @Test
    void deveExcluirInscricao() {
        Long id = criarInscricao(criarParticipante("Joao Souza", "joao@email.com"), criarEvento()).getId();
        assertTrue(repository.existsById(id));

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }
}
