/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Filme;
import org.catolica.prog4.persistencia.entities.Genero;

/**
 *
 * @author Geoavne
 */
public class FilmeJpaController implements Serializable {

    public FilmeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Filme filme) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero genero = filme.getGenero();
            if (genero != null) {
                genero = em.getReference(genero.getClass(), genero.getId());
                filme.setGenero(genero);
            }
            em.persist(filme);
            if (genero != null) {
                genero.getFilmes().add(filme);
                genero = em.merge(genero);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Filme filme) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Filme persistentFilme = em.find(Filme.class, filme.getId());
            Genero generoOld = persistentFilme.getGenero();
            Genero generoNew = filme.getGenero();
            if (generoNew != null) {
                generoNew = em.getReference(generoNew.getClass(), generoNew.getId());
                filme.setGenero(generoNew);
            }
            filme = em.merge(filme);
            if (generoOld != null && !generoOld.equals(generoNew)) {
                generoOld.getFilmes().remove(filme);
                generoOld = em.merge(generoOld);
            }
            if (generoNew != null && !generoNew.equals(generoOld)) {
                generoNew.getFilmes().add(filme);
                generoNew = em.merge(generoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = filme.getId();
                if (findFilme(id) == null) {
                    throw new NonexistentEntityException("The filme with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Filme filme;
            try {
                filme = em.getReference(Filme.class, id);
                filme.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filme with id " + id + " no longer exists.", enfe);
            }
            Genero genero = filme.getGenero();
            if (genero != null) {
                genero.getFilmes().remove(filme);
                genero = em.merge(genero);
            }
            em.remove(filme);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Filme> findFilmeEntities() {
        return findFilmeEntities(true, -1, -1);
    }

    public List<Filme> findFilmeEntities(int maxResults, int firstResult) {
        return findFilmeEntities(false, maxResults, firstResult);
    }

    private List<Filme> findFilmeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Filme.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Filme findFilme(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Filme.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilmeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Filme> rt = cq.from(Filme.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
