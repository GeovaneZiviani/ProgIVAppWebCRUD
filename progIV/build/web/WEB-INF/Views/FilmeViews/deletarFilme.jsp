<%-- 
    Document   : deletarGenero
    Created on : 30/10/2016, 16:50:44
    Author     : geovane
--%>
<title>Deletar Filme</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Deleta o Filme?</h1>
<hr />
<form action="mvc?cmd=FilmeCmd&action=excluido" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
            <input class="form-control" type="text" name="id" value="<c:out value="${filme.id}" />" readonly="readonly" placeholder="Filme ID" />    
        </dd>
        <dt>
            <p><strong>Titulo: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${filme.titulo}" /></td>
        </dd>
        <dt>
            <p><strong>Diretor: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${filme.diretor}" /></td>
        </dd>
        <dt>
            <p><strong> Ano de Lançameto: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${filme.anoEdicao}" /></td>
        </dd>
   
        <dt>
            <p><strong> Genero: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${filme.genero.nome}" /></td>
        </dd>


    </dl>
    <hr />
    <p>
        <button type="submit" class="btn btn-danger" >Excluir</button> | 
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>