/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.interfaces;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Filme;
import org.catolica.prog4.persistencia.entities.Game;

/**
 *
 * @author Geoavne
 */
public interface IGame {
      void create(Game game);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Game game) throws NonexistentEntityException, Exception;

    Game findGame(Long id);

    List<Game> findGameEntities();

    List<Game> findGameEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getGameCount();

    List<Game> findAll();
    
    List<Game> findGame(String pesquisa) throws NoResultException;
}
