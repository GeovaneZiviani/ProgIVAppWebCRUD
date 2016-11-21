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

/**
 *
 * @author Administrador
 */
public interface IFilmeDAO {
    
    void create(Filme Filme);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Filme filme) throws NonexistentEntityException, Exception;

    Filme findFilme(Long id);

    List<Filme> findFilmeEntities();

    List<Filme> findFilmeEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getFilmeCount();

    List<Filme> findAll();
    
    List<Filme> findFilme(String pesquisa) throws NoResultException;
}
