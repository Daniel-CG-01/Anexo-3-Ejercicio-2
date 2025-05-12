package com.example;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.model.Pelicula;
import com.example.model.Actor;
import com.example.dao.PeliculaDAO;
import com.example.dao.ActorDAO;
import com.example.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            ActorDAO actorDAO = new ActorDAO();
            PeliculaDAO peliculaDAO = new PeliculaDAO();

            Actor act1 = new Actor();
            Actor act2 = new Actor();
            Actor act3 = new Actor();

            act1.setNombreActor("Mark Hamill");

            act2.setNombreActor("Harrison Ford");

            act3.setNombreActor("Carrie Fisher");

            Pelicula pel1 = new Pelicula();
            Pelicula pel2 = new Pelicula();
            Pelicula pel3 = new Pelicula();

            pel1.setNombrePelicula("Star Wars: Una Nueva Esperanza");

            pel2.setNombrePelicula("Star Wars: El Imperio Contraataca");

            pel3.setNombrePelicula("Star Wars: El Retorno del Jedi");

            //Asociaciones
            act1.anyadirPelicula(pel3);
            act2.anyadirPelicula(pel2);
            act3.anyadirPelicula(pel1);

            //Insertamos actores (las películas se insertan en cascada)
            actorDAO.insertActor(session, act1);
            actorDAO.insertActor(session, act2);
            actorDAO.insertActor(session, act3);

            transaction.commit();
            session.clear(); //Para eliminar datos de caché (y así no salen en futuras consultas)

            List<Actor> actores = actorDAO.selectAllActores(session);

            for (Actor a : actores) {
                System.out.print(a.getNombreActor()+": ");
                for (Pelicula p : a.getPeliculas()) {
                    System.out.println(p.getNombrePelicula()+" ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}