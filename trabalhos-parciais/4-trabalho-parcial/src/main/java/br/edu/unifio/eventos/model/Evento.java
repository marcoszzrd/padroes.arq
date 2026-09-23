package br.edu.unifio.eventos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private LocalDate dataEvento;

    // Um evento pertence a uma categoria
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Um evento acontece em um local
    @ManyToOne(optional = false)
    @JoinColumn(name = "local_id")
    private Local local;

    // Um evento pode ter varios palestrantes (e um palestrante varios eventos)
    @ManyToMany
    @JoinTable(name = "evento_palestrante",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "palestrante_id"))
    private Set<Palestrante> palestrantes = new HashSet<>();

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Local getLocal() { return local; }
    public void setLocal(Local local) { this.local = local; }

    public Set<Palestrante> getPalestrantes() { return palestrantes; }
    public void setPalestrantes(Set<Palestrante> palestrantes) { this.palestrantes = palestrantes; }
}
