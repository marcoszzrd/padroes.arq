package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository ja traz: save, findById, findAll, deleteById, existsById, count...
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
