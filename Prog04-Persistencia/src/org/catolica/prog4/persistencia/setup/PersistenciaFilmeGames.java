package org.catolica.prog4.persistencia.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.catolica.prog4.persistencia.daos.FilmeDAO;
import org.catolica.prog4.persistencia.daos.GameDAO;
import org.catolica.prog4.persistencia.daos.GeneroDAO;

import org.catolica.prog4.persistencia.daos.RuleDAO;
import org.catolica.prog4.persistencia.daos.UserDAO;
import org.catolica.prog4.persistencia.entities.Filme;
import org.catolica.prog4.persistencia.entities.Game;
import org.catolica.prog4.persistencia.entities.Genero;

import org.catolica.prog4.persistencia.entities.Rule;
import org.catolica.prog4.persistencia.entities.User;

public class PersistenciaFilmeGames {

    public static void main(String[] args) {
        
       // createFilmeTest();
        
         createGameTest();
        
        //createRuletest();
        //findAllUsersTest();
        //findAllRulesTest();
        //updateAllUsersTest();
        //findAllUsersTest();
        //findAllRulesTest();
    }

    private static void createFilmeTest() {
        System.out.println("\n createFilmeTest...");

        Map<String, String> datas = new HashMap<>(8);
        datas.put("tese", "Ação");
        datas.put("aa", "Suspense");
        datas.put("Skr", "Aventura");
        datas.put("rola", "Terror");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        FilmeDAO filmeDAO = new FilmeDAO(factory);
        List<Genero> generos = new GeneroDAO(factory).findAll();
        filmeDAO.findFilmeEntities();
        for (Map.Entry<String, String> data : datas.entrySet()) {
            Filme o = new Filme();
            o.setTitulo(data.getKey());
            o.setDiretor("test");
            o.setAnoEdicao(2000);
            for (Genero genero : generos) {
                if (genero.getNome().equalsIgnoreCase(data.getValue())) {
                    o.setGenero(genero);
                    break;
                }
            }
            filmeDAO.create(o);
        }
    }

      private static void createGameTest() {
        System.out.println("\n createFilmeTest...");

        Map<String, String> datas = new HashMap<>(8);
        datas.put("Mario", "Ação");
        datas.put("Donkey Kong", "Suspense");
        datas.put("Mega man", "Aventura");
        datas.put("Dark Souls", "Terror");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        GameDAO gameDAO = new GameDAO(factory);
        List<Genero> generos = new GeneroDAO(factory).findAll();
        gameDAO.findGameEntities();
        for (Map.Entry<String, String> data : datas.entrySet()) {
            Game o = new Game();
            o.setTitulo(data.getKey());          
            o.setLancamento(2000);
            for (Genero genero : generos) {
                if (genero.getNome().equalsIgnoreCase(data.getValue())) {
                    o.setGenero(genero);
                    break;
                }
            }
            gameDAO.create(o);
        }
    }
    
    private static void findAllUsersTest() {
        System.out.println("\nfindAllUsersTest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findAll();
        lst.stream().forEach((o) -> {
            System.out.println(o);
        });
    }

    private static void findAllRulesTest() {
        System.out.println("\n findAllRulesTest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        RuleDAO dao = new RuleDAO(factory);
        List<Rule> lst = dao.findRuleEntities();
        lst.stream().forEach((o) -> {
            System.out.println(o);
        });
    }

    private static void createUserTest() {
        System.out.println("\ncreateTest...");
        String[] data = {"Fabio", "João", "José", "Galateo", "Bill"};
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findUserEntities();
        for (String data1 : data) {
            User o = new User();
            o.setNome(data1);
            o.setEmail(data1 + "@" + "prog4.net");
            o.setSenha(data1 + "#12345");
            dao.create(o);
        }
        User o = new User();
        o.setNome("1");
        o.setEmail("1");
        o.setSenha("1");
        dao.create(o);
    }

    private static void createRuletest() {
        System.out.println("\ncreateRuletest...");
        String[] data = {"Administrador", "Comprador", "Vendedor", "Gerente", "Diretor"};
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        RuleDAO dao = new RuleDAO(factory);
        List<Rule> lst = dao.findRuleEntities();
        for (String data1 : data) {
            Rule o = new Rule();
            o.setNome(data1);
            dao.create(o);
        }
    }
    //==========================================================================================

    //========================================================================================== 
    private static void updateAllUsersTest() {
        System.out.println("\nupdateAllUsersTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findAll();
        lst.stream().map((o) -> {
            o.setNome(o.getNome() + ".");
            return o;
        }).forEach((o) -> {
            try {
                dao.edit(o);
            } catch (Exception ex) {
                //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
            }
        });
    }

    private static void deleteAllUsersTest() {
        System.out.println("\ndeleteAllUsersTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findAll();
        for (User o : lst) {
            try {
                dao.destroy(o.getId());
            } catch (Exception ex) {
                //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
            }
        }
    }

    private static void deleteAllRuleTest() {
        System.out.println("\ndeleteAllRuleTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        RuleDAO dao = new RuleDAO(factory);
        List<Rule> lst = dao.findRuleEntities();
        for (Rule o : lst) {
            try {
                dao.destroy(o.getId());
            } catch (Exception ex) {
                // Logger.getLogger(TestarPersistencia.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    private static User autenticar(String email, String passwd) {
        final String PERSISTENCE_UNIT_NAME = "PersistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        User user;
        try {
            user = dao.findUser(email, passwd);
        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

}
