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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.UserDAO;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.User;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

public class UserCmd extends AbstractWebCmd implements IWebCmd {

    private static final String list = "/WEB-INF/Views/UserViews/listarUser.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/Views/UserViews/adicionar_editarUser.jsp";
    private static final String detalhes = "/WEB-INF/Views/UserViews/detalhesUser.jsp";
    private static final String deletar = "/WEB-INF/Views/UserViews/deletarUser.jsp";
    private String forward = "";

    private final UserDAO dao;
    private Long id;
    private User user;

    public UserCmd() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        dao = new UserDAO(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        id = null;
        user = null;
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
                    dao.destroy(id);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(UserCmd.class.getName()).log(Level.SEVERE, null, ex);
                }
                recarregarListagem(request);
                break;
            case "editar":

                request.setAttribute("tAdEd", "Editar");
                repassarParamentroParaTela(request, INSERT_OR_EDIT);

                break;
            case "editado":

                user = new User();
                String generoId = request.getParameter("id");
                user.setNome(request.getParameter("nome"));
                
                user.setEmail(request.getParameter("email"));
                user.setSenha(request.getParameter("senha"));
                
                if (generoId == null || generoId.isEmpty()) {
                    dao.create(user);
                } else {
                    user.setId(Long.parseLong(generoId));
                    try {
                        dao.edit(user);
                    } catch (Exception ex) {
                        Logger.getLogger(UserCmd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                recarregarListagem(request);

                break;
            case "adicionar":
                request.setAttribute("tAdEd", "Adicionar");
                forward = INSERT_OR_EDIT;
                break;
            case "procurar":

                String conteudo = request.getParameter("cProcurar");
                if (conteudo == null || conteudo.isEmpty()) {
                    recarregarListagem(request);
                } else {
                    List<User> users;
                    users = new ArrayList<>();
                    users = dao.findUsers(conteudo);
                    request.setAttribute("users", users);
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
        request.setAttribute("users", this.allUser());
    }

    private List<User> allUser() {

        List<User> lst = dao.findUserEntities();

        return lst;
    }

    private void repassarParamentroParaTela(HttpServletRequest request, String tela) {
        id = Long.parseLong(request.getParameter("id"));
        user = dao.findUser(id);
        request.setAttribute("users", user);
        forward = tela;
    }

}