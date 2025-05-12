package com.example.model;

import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="pelicula")
public class Pelicula {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    int idPelicula;

    @Column(name="name")
    String nombrePelicula;

    @ManyToMany(mappedBy = "peliculas", fetch = FetchType.EAGER)
	private Set<Actor> actores = new HashSet<>();
	
	public void anyadirActor(Actor a) {
		actores.add(a);
		a.getPeliculas().add(this);
	}
	
	public void quitarActor(Actor a) {
		actores.remove(a);
		a.getPeliculas().add(this);
	}
}