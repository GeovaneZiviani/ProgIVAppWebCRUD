<%-- 
    Document   : editarGenero
    Created on : 30/10/2016, 16:20:24
    Author     : Geovane Camargo
--%>


<title>Adicionar/Editar Usuario</title>
<!-- Bootstraps -->
<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>${tAdEd} user</h1>
<hr />
<dl class="dl-horizontal">
    <form action="mvc?cmd=UserCmd&action=editado" method="post">
        <div class="row">
            <div class="form-group col-xs-3">
                <label for="id"> ID</label> 
                <input class="form-control" type="text" name="id" value="<c:out value="${user.id}" />" readonly="readonly" placeholder="User ID" />
            </div>

            <div class="form-group col-xs-8">
                <label for="email">E-mail</label> 
                <input class="form-control" type="email"  required="required" name="email" value="<c:out value="${user.email}" />" placeholder="E-Mail" />
            </div>

        </div>

        <div class="row">
            <div class="form-group col-xs-3">
                <label for="nome">Nome</label> 
                <input class="form-control" type="text"  name="nome" value="<c:out value="${user.nome}" />" placeholder="Nome" />
            </div>

            <div class="form-group col-xs-3">
                <label for="senha">Senha</label> 
                <input class="form-control" type="password"  id = "ano" name="senha" value="<c:out value="${user.senha}" />" placeholder="Senha" />

            </div>
        </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success" >Salvar</button> | 
                <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
            </div>
    </form>
</dl>

<%@include file="/WEB-INF/jspf/footer.jspf"%>

