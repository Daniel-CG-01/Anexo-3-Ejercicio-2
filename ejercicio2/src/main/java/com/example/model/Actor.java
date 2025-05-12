package com.example.model;

import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="actor")
public class Actor {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    int idActor;

    @Column(name="nombre")
    String nombreActor;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "actor_pelicula", joinColumns = @JoinColumn(name = "idActor"), inverseJoinColumns = @JoinColumn(name = "idPelicula"))
	private Set<Pelicula> peliculas = new HashSet<>();

    public void anyadirPelicula(Pelicula p) {
		peliculas.add(p);
        p.getActores().add(this);
	}
	
	public void quitarPelicula(Pelicula p) {
		peliculas.remove(p);
		p.getActores().add(this);
	}
}