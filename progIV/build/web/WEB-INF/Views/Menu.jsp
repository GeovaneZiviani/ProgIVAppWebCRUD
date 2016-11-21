<%-- 
    Document   : Menu
    Created on : 27/10/2016, 20:01:39
    Author     : geovane.camargo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <!-- Bootstrap -->

        <%@include file="/WEB-INF/jspf/head.jspf"%>


    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf"%>
        <!---barra de navegação -->
        <div>
            <header>
                <nav class="navbar-inverse navbar-default">

                </nav>
            </header>

        </div>
        <div id="main" class="container-fluid">
            <BR>
            <h1 class="page-header">Loca Nois</h1>
            <!-- FORM MAIN -->
        </div>

        <div class="row">
            <div class="form-group col-md-4">
                <label class="col-md-4">Locadora  e Gamer House</label>
                
            </div>
        </div><!-- /LINHA-1 -->
        <%@include file="/WEB-INF/jspf/modal.jspf"%>

        <%@include file="/WEB-INF/jspf/footer.jspf"%>

        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>
    </body>
</html>
