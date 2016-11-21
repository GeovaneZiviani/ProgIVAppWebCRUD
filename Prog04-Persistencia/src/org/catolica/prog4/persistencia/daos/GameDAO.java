/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.entities.Game;
import org.catolica.prog4.persistencia.interfaces.IGame;

/**
 *
 * @author Geoavne
 */
public class GameDAO extends GameJpaController implements IGame {

    public GameDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Game> findAll() {
        return super.findGameEntities();
    }

    @Override
    public List<Game> findGame(String pesquisa) throws NoResultException {
        EntityManager em = getEntityManager();
        List<Game> games = null;
        games = new ArrayList<>();
        try {
            games = em.createNamedQuery("Game.procurar")
                    .setParameter("titulo", "%" + pesquisa + "%")
                    .setMaxResults(10)
                    .getResultList();
        } finally {
            em.close();
        }

        return games;
    }

}
