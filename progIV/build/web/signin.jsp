<%-- 
    Document   : SenhaLogin
    Created on : 17/10/2016, 19:41:22
    Author     : geovane.camargo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="PM APP">
        <meta name="author" content="Geovane Camargo">
        <link rel="icon" href="assets/icons/qb-icon.png">
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="assets/core/bootstrap-3.3.5-dist/css/Test.css" rel="stylesheet">

    </head>
    <div class="container">
        <div class="card card-container">
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form  class="form-signin" id="formAddNew" name="formAddNew" method="POST" action="signin"> 
                <input type="hidden" id="cmd" name="cmd" value="adicionar">

                <span id="reauth-email" class="reauth-email"></span>
                <input type="email" name="email" id="email" class="form-control" placeholder="Email address" required autofocus>
                <input type="password" name="senha"  id="senha" class="form-control" placeholder="Password" required>
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
                ${msg}
            </form><!-- /form -->


            <a href="#" class="forgot-password">
                Forgot the password?
            </a>
        </div><!-- /card-container -->
    </div><!-- /container -->
    <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>        
    <script type="text/javascript" src="assets/js/LoginReve.js"></script>

</body>
</html>
