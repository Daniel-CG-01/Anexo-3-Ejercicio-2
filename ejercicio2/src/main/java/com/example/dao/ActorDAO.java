package com.example.dao;

import java.util.List;
import org.hibernate.Session;
import com.example.model.Actor;

public class ActorDAO {
    
    //Inserción
	public void insertActor(Session session, Actor a) {
		session.persist(a);
		System.out.println("¡Actor insertado!");
	}
	
	//Actualización
	public void updateActor(Session session, Actor a) {
		session.merge(a);
		System.out.println("¡Actor actualizado!");
	}
	
	//Borrado
	public void deleteActor(Session session, int id) {
		Actor a = session.get(Actor.class, id);
		if (a != null) {
			session.remove(a);
			System.out.println("¡Actor borrado!");
		}
	}
	
	//Selección múltiple
	public List<Actor> selectAllActores (Session session) {
		return session.createQuery("FROM Actor", Actor.class).list();
	}
}