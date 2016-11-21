/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.cmds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.FilmeDAO;
import org.catolica.prog4.persistencia.daos.GameDAO;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Filme;
import org.catolica.prog4.persistencia.entities.Game;
import org.catolica.prog4.persistencia.entities.Genero;

import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author geovane
 */
public class GameCmd extends AbstractWebCmd implements IWebCmd {

    private static final String ERRO = "erro";
    private static final String MENSAGEM = "msg";

    private static final String list = "/WEB-INF/Views/GameViews/listarGames.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/Views/GameViews/adicionar_editarGames.jsp";
    private static final String detalhes = "/WEB-INF/Views/GameViews/detalhesGame.jsp";
    private static final String deletar = "/WEB-INF/Views/GameViews/deletarGame.jsp";
    private String forward = "";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
    private final GameDAO gameDao;
    private final GeneroDAO generoDao;
    private Long id;
    private Game game;
    private List<Genero> generos;

    public GameCmd() {
        generoDao = new GeneroDAO(factory);
        gameDao = new GameDAO(factory);
        generos = new ArrayList<>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        id = null;
        game = null;
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "detalhes":
                repassarParamentroParaTela(request, detalhes);
                break;
            case "deletar?":
                repassarParamentroParaTela(request, deletar);
                break;
            case "excluido":
                id = Long.parseLong(request.getParameter("id"));
                try {
                    gameDao.destroy(id);
                    request.setAttribute(MENSAGEM, " Jogo excluido com sucesso");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(GameCmd.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute(ERRO, "Erro ao excluir");
                }
                recarregarListagem(request);
                break;
            case "editar":

                request.setAttribute("tAdEd", "Editar");
                repassarParamentroParaTela(request, INSERT_OR_EDIT);

                break;
            case "editado":

                game = new Game();
                String gameId = request.getParameter("id");
                game.setTitulo(request.getParameter("titulo"));
                Genero genero = new Genero();
                genero = generoDao.findGenero(Long.parseLong(request.getParameter("comboGeneros")));
                game.setGenero(genero);

                if (request.getParameter("ano") != null) {
                    game.setLancamento(Integer.parseInt(request.getParameter("ano")));
                }

                generos = generoDao.findAll();
                game.setGenero(generos.get(0));

                if (gameId == null || gameId.isEmpty()) {
                    gameDao.create(game);
                    request.setAttribute(MENSAGEM, "jogo foi adicionado com sucesso");
                } else {
                    game.setId(Long.parseLong(gameId));
                    try {
                        gameDao.edit(game);
                        request.setAttribute(MENSAGEM, "Jogo editado com sucesso");
                    } catch (Exception ex) {
                        Logger.getLogger(FilmeCmd.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute(ERRO, "Jogo editado com sucesso");
                    }
                }

                recarregarListagem(request);

                break;
            case "adicionar":
                request.setAttribute("tAdEd", "Adicionar");
                generos = generoDao.findAll();
                request.setAttribute("comboGeneros", generos);
                forward = INSERT_OR_EDIT;
                break;
            case "procurar":

                String conteudo = request.getParameter("cProcurar");
                if (conteudo == null || conteudo.isEmpty()) {
                    recarregarListagem(request);
                } else {
                    List<Game> games;
                    games = new ArrayList<>();
                    games =gameDao.findGame(conteudo);
                    request.setAttribute("games", games);
                }

                forward = list;
                break;
            default:
                recarregarListagem(request);
                break;
        }

        return forward;
    }

    public void recarregarListagem(HttpServletRequest request) {
        forward = list;
        request.setAttribute("games", this.allGames());
    }

    private List<Game> allGames() {

        List<Game> lst = gameDao.findGameEntities();

        return lst;
    }

    private void repassarParamentroParaTela(HttpServletRequest request, String tela) {
        id = Long.parseLong(request.getParameter("id"));
        game = gameDao.findGame(id);
        generos = generoDao.findAll();
        request.setAttribute("comboGeneros", generos);
        request.setAttribute("idSelecionado", game.getGenero().getId());
        request.setAttribute("game", game);
        forward = tela;
    }

    private List<Game> recuperarFilmeRelacionadoFilme(Long IdGame) {
        EntityManager em = null;
        em = gameDao.getEntityManager();
        em.getTransaction().begin();
        Game persistGame = em.find(Game.class, IdGame);

        List<Game> games = null;

        return games;
    }

}
