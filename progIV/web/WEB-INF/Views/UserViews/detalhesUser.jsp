<%-- 
    Document   : detalhesGenero
    Created on : 03/11/2016, 13:26:22
    Author     : Geovane Camargo
--%>
<title>Detalhes Usuario</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Detalhes Usuario</h1>
<hr />
<form action="mvc?cmd=UserCmd&action=editado" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
            <input class="form-control" type="text" name="id" value="<c:out value="${user.id}" />" readonly="readonly" placeholder="User ID" />    
        </dd>
        <dt>
            <p><strong>E-mail:</strong></p>
        </dt>
        <dd>
        <td><c:out value="${user.email}" /></td>
        </dd>
        <dt>
            <p><strong>Nome</strong></p>
        </dt>

        <dd>
        <td><c:out value="${user.nome}" /></td>
        </dd>

    </dl>
    <hr />
    <p>
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>

