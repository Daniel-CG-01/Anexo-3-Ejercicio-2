package com.example.dao;

import java.util.List;
import org.hibernate.Session;
import com.example.model.Pelicula;

public class PeliculaDAO {

    //Inserción
	public void insertPelicula(Session session, Pelicula p) {
		session.persist(p);
		System.out.println("¡Película insertado!");
	}
	
	//Actualización
	public void updatePelicula(Session session, Pelicula p) {
		session.merge(p);
		System.out.println("¡Película actualizado!");
	}
	
	//Borrado
	public void deletePelicula(Session session, int id) {
		Pelicula p = session.get(Pelicula.class, id);
		if (p != null) {
			session.remove(p);
			System.out.println("¡Película borrado!");
		}
	}
	
	//Selección múltiple
	public List<Pelicula> selectAllPeliculas (Session session) {
		return session.createQuery("FROM Pelicula", Pelicula.class).list();
	}
}