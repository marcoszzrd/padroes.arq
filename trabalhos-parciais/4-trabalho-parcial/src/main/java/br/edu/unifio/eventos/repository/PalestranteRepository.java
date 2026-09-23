package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository ja traz: save, findById, findAll, deleteById, existsById, count...
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {
}
