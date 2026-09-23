package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository ja traz: save, findById, findAll, deleteById, existsById, count...
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}
