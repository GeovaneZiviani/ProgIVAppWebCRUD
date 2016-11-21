<%-- 
    Document   : detalhesGenero
    Created on : 03/11/2016, 13:26:22
    Author     : geovane
--%>
<title>Detalhes Filme</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Detalhes Jogo</h1>
<hr />
<form action="mvc?cmd=GameCmd&action=editado" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
        <td name="id"><c:out value="${game.id}" /></td>
        </dd>
        <dt>
            <p><strong>Titulo: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${game.titulo}" /></td>
        </dd>

        <dt>
            <p><strong> Ano de lançamento : </strong></p>
        </dt>

        <dd>
        <td><c:out value="${game.lancamento}" /></td>
        </dd>

        <dt>
            <p><strong>  Genero: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${game.genero.nome}" /></td>
        </dd>


    </dl>
    <hr />
    <p>
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>

