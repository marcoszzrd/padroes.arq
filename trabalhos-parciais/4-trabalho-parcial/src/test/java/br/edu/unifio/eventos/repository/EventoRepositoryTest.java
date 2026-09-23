package br.edu.unifio.eventos.repository;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.unifio.eventos.model.Categoria;
import br.edu.unifio.eventos.model.Evento;
import br.edu.unifio.eventos.model.Local;
import br.edu.unifio.eventos.model.Palestrante;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class EventoRepositoryTest {

    @Autowired
    private EventoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private LocalRepository localRepository;
    @Autowired
    private PalestranteRepository palestranteRepository;
    @Autowired
    private TestEntityManager em;

    // ---- Metodos auxiliares: criam os registros dos quais o Evento depende ----
    private Categoria criarCategoria() {
        Categoria c = new Categoria();
        c.setNome("Tecnologia");
        c.setDescricao("Eventos de tecnologia");
        return categoriaRepository.save(c);
    }

    private Local criarLocal() {
        Local l = new Local();
        l.setNome("Auditorio Central");
        l.setCidade("Ourinhos");
        l.setCapacidade(200);
        return localRepository.save(l);
    }

    private Palestrante criarPalestrante() {
        Palestrante p = new Palestrante();
        p.setNome("Maria Silva");
        p.setEmail("maria@email.com");
        return palestranteRepository.save(p);
    }

    private Evento criarEvento(String titulo) {
        Evento e = new Evento();
        e.setTitulo(titulo);
        e.setDataEvento(LocalDate.of(2026, 11, 10));
        e.setCategoria(criarCategoria());
        e.setLocal(criarLocal());
        e.getPalestrantes().add(criarPalestrante());
        return repository.save(e);
    }

    @Test
    void deveInserirEvento() {
        Categoria categoria = criarCategoria();
        Local local = criarLocal();
        Palestrante palestrante = criarPalestrante();

        Evento evento = new Evento();
        evento.setTitulo("Semana de Tecnologia");
        evento.setDataEvento(LocalDate.of(2026, 11, 10));
        evento.setCategoria(categoria);   // relacionamento com Categoria
        evento.setLocal(local);           // relacionamento com Local
        evento.getPalestrantes().add(palestrante); // relacionamento com Palestrante

        Evento salvo = repository.save(evento);

        assertNotNull(salvo.getId());
        assertEquals("Semana de Tecnologia", salvo.getTitulo());
        assertEquals(categoria.getId(), salvo.getCategoria().getId());
        assertEquals(local.getId(), salvo.getLocal().getId());
        assertEquals(1, salvo.getPalestrantes().size());
    }

    @Test
    void deveBuscarEventoPorId() {
        Long id = criarEvento("Semana de Tecnologia").getId();
        em.flush();
        em.clear();

        Evento encontrado = repository.findById(id).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Semana de Tecnologia", encontrado.getTitulo());
        assertEquals(LocalDate.of(2026, 11, 10), encontrado.getDataEvento());
        assertEquals("Tecnologia", encontrado.getCategoria().getNome());
        assertEquals("Auditorio Central", encontrado.getLocal().getNome());
        assertEquals(1, encontrado.getPalestrantes().size());
    }

    @Test
    void deveListarEventos() {
        criarEvento("Semana de Tecnologia");
        criarEvento("Congresso de Inovacao");

        List<Evento> lista = repository.findAll();

        assertEquals(2, lista.size());
        assertTrue(lista.stream().anyMatch(e -> e.getTitulo().equals("Semana de Tecnologia")));
        assertTrue(lista.stream().anyMatch(e -> e.getTitulo().equals("Congresso de Inovacao")));
    }

    @Test
    void deveAlterarEvento() {
        Evento evento = criarEvento("Semana de Tecnologia");
        Long id = evento.getId();

        evento.setTitulo("Semana de Tecnologia 2026");
        repository.saveAndFlush(evento);
        em.clear();

        Evento alterado = repository.findById(id).orElseThrow();
        assertEquals("Semana de Tecnologia 2026", alterado.getTitulo());
        assertEquals(1, repository.count());
    }

    @Test
    void deveExcluirEvento() {
        Long id = criarEvento("Semana de Tecnologia").getId();
        assertTrue(repository.existsById(id));

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }
}
