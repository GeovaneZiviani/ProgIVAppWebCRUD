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
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Filme;
import org.catolica.prog4.persistencia.entities.Genero;

import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author geovane
 */
public class FilmeCmd extends AbstractWebCmd implements IWebCmd {

    private static final String ERRO = "erro";
    private static final String MENSAGEM = "msg";

    private static final String list = "/WEB-INF/Views/FilmeViews/listarFilme.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/Views/FilmeViews/adicionar_editarFilme.jsp";
    private static final String detalhes = "/WEB-INF/Views/FilmeViews/detalhesFilme.jsp";
    private static final String deletar = "/WEB-INF/Views/FilmeViews/deletarFilme.jsp";
    private String forward = "";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
    private final FilmeDAO filmeDao;
    private final GeneroDAO generoDao;
    private Long id;
    private Filme filme;
    private List<Genero> generos;

    public FilmeCmd() {
        generoDao = new GeneroDAO(factory);
        filmeDao = new FilmeDAO(factory);
        generos = new ArrayList<>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        id = null;
        filme = null;
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
                    filmeDao.destroy(id);
                    request.setAttribute(MENSAGEM, "Filme excluido com sucesso");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(FilmeCmd.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute(ERRO, "Erro ao excluir");
                }
                recarregarListagem(request);
                break;
            case "editar":

                request.setAttribute("tAdEd", "Editar");
                repassarParamentroParaTela(request, INSERT_OR_EDIT);

                break;
            case "editado":

                filme = new Filme();
                String filmeId = request.getParameter("id");
                filme.setTitulo(request.getParameter("titulo"));
                Genero genero = new Genero();
                genero = generoDao.findGenero(Long.parseLong(request.getParameter("comboGeneros")));
                filme.setGenero(genero);
                if (request.getParameter("diretor") != null) {
                    filme.setDiretor(request.getParameter("diretor"));
                }
                if (request.getParameter("ano") != null) {
                    filme.setAnoEdicao(Integer.parseInt(request.getParameter("ano")));
                }

                generos = generoDao.findAll();
                filme.setGenero(generos.get(0));

                if (filmeId == null || filmeId.isEmpty()) {
                    filmeDao.create(filme);
                    request.setAttribute(MENSAGEM, "Filme foi adicionado com sucesso");
                } else {
                    filme.setId(Long.parseLong(filmeId));
                    try {
                        filmeDao.edit(filme);
                        request.setAttribute(MENSAGEM, "Filme editado com sucesso");
                    } catch (Exception ex) {
                        Logger.getLogger(FilmeCmd.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute(ERRO, "Filme editado com sucesso");
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
                    List<Filme> filmes;
                    filmes = new ArrayList<>();
                    filmes = filmeDao.findFilme(conteudo);
                    request.setAttribute("filmes", filmes);
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
        request.setAttribute("filmes", this.allFilmes());
    }

    private List<Filme> allFilmes() {

        List<Filme> lst = filmeDao.findFilmeEntities();

        return lst;
    }

    private void repassarParamentroParaTela(HttpServletRequest request, String tela) {
        id = Long.parseLong(request.getParameter("id"));
        filme = filmeDao.findFilme(id);
        generos = generoDao.findAll();
        request.setAttribute("comboGeneros", generos);
        request.setAttribute("idSelecionado", filme.getGenero().getId());
        request.setAttribute("filme", filme);
        forward = tela;
    }

    private List<Filme> recuperarFilmeRelacionadoFilme(Long idFilme) {
        EntityManager em = null;
        em = filmeDao.getEntityManager();
        em.getTransaction().begin();
        Filme persistentFilme = em.find(Filme.class, idFilme);

        List<Filme> Filmes = null;

        return Filmes;
    }

    private List<Rule> findAllRules() {
        List<Rule> lst = new ArrayList<>();
        lst.add(new Rule(1L, "Administrador"));
        lst.add(new Rule(2L, "Usuário"));
        lst.add(new Rule(3L, "Visitante "));

        return lst;
    }

}
