<%-- 
    Document   : generos
    Created on : 30/10/2016, 16:14:32
    Author     : Geovane Camargo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Usuarios</title>
        <%@include file="/WEB-INF/jspf/head.jspf"%>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf"%>
        <div  class="container-fluid">


            <h1>Usuarios</h1>
            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <p>
                            <a href="mvc?cmd=UserCmd&action=adicionar" data-toggle="modal" data-target="#util-modal"  class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                Adicionar</a>
                        </p>
                    </div><!-- /input-group -->
                </div><!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <form action="mvc?cmd=UserCmd&action=procurar" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Procurar pelo Nome" id="cProcurar" name="cProcurar"/>
                            <span class="input-group-btn">

                                <a class="btn btn-warning" type="submit" value="Ir" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                            </span>
                        </div>
                    </form>
                </div>
            </div>


            <div id="list" class="row">

                <div class="table-responsive  col-md-12">
                    <table class="table  table-striped" cellspacing="0" cellpadding="0">
                        <thead >
                            <tr >
                                <th class ="col-md-4 ">ID</th>
                                <th class ="col-md-4 ">E-mail</th>
                                <th class ="col-md-4 ">Nome</th>
                                <th class="actions col-md-4 ">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tbody>
                            <c:forEach var="o" items="${users}">
                                <tr>
                                    <td><c:out value="${o.id}" /></td>
                                    <td><c:out value="${o.email}" /></td>
                                    <td><c:out value="${o.nome}" /></td>

                                    <td class="actions">
                                        <a href="mvc?cmd=UserCmd&action=detalhes&id=<c:out value="${o.id}"/>" class="btn btn-success" data-toggle="modal" data-target="#util-modal" data-placement="left" >
                                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                                        </a> 
                                        <a href="mvc?cmd=UserCmd&action=editar&id=<c:out value="${o.id}"/>" class="btn btn-warning" data-toggle="modal" data-target="#util-modal" data-placement="top" >
                                            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                        </a> 
                                        <a href="mvc?cmd=UserCmd&action=deletar?&id=<c:out value="${o.id}"/>" class="btn btn-danger"  data-toggle="modal" data-target="#util-modal" data-placement="right" >
                                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        </a> 

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="bottom" class="row">
                        <div class="col-md-12">

                            <ul class="pagination">
                                <li class="disabled"><a>&lt; Anterior</a></li>
                                <li class="disabled"><a>1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
                            </ul><!-- /.pagination -->

                        </div>
                    </div> <!-- /#bottom -->

                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/jspf/modal.jspf"%>
        <!-- CORE JAVASCRIPT LYBRARIES...
================================================== -->
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </body>
</html>
