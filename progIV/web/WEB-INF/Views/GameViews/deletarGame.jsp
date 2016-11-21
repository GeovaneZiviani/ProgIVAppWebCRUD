<%-- 
    Document   : deletarGenero
    Created on : 30/10/2016, 16:50:44
    Author     : geovane
--%>
<title>Deletar jogo</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Deleta o jogo?</h1>
<hr />
<form action="mvc?cmd=GameCmd&action=excluido" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
            <input class="form-control" type="text" name="id" value="<c:out value="${game.id}" />" readonly="readonly" placeholder="Game ID" />    
        </dd>
        <dt>
            <p><strong>Titulo: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${game.titulo}" /></td>
        </dd>
       
        <dt>
            <p><strong> Ano de Lanšameto: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${Game.lancamento}" /></td>
        </dd>
   
        <dt>
            <p><strong> Genero: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${Game.genero.nome}" /></td>
        </dd>


    </dl>
    <hr />
    <p>
        <button type="submit" class="btn btn-danger" >Excluir</button> | 
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>