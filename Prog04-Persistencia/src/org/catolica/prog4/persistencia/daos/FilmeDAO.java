/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

/*package org.catolica.prog4.persistencia.daos;*/
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.entities.Filme;
import org.catolica.prog4.persistencia.interfaces.IFilmeDAO;

/**
 *
 * @author geovane camargo
 */
public class FilmeDAO extends FilmeJpaController implements IFilmeDAO {

    public FilmeDAO(EntityManagerFactory emf) {
        super(emf);
    }

        //return generos;

    @Override
    public List<Filme> findAll() {
         return super.findFilmeEntities();
    }

    @Override
    public List<Filme> findFilme(String pesquisa) throws NoResultException {
         EntityManager em = getEntityManager();
        List<Filme> filmes = null;
        filmes = new ArrayList<>();
        try {
            filmes = em.createNamedQuery("Filme.procurar")
                    .setParameter("titulo", "%" + pesquisa + "%")
                    .setMaxResults(10)
                    .getResultList();
        } finally {
            em.close();
        }

        return filmes;
    }
    
}
