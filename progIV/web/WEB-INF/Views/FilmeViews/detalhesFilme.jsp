<%-- 
    Document   : detalhesGenero
    Created on : 03/11/2016, 13:26:22
    Author     : geovane
--%>
         <title>Detalhes Filme</title>

        <%@include file="/WEB-INF/jspf/head.jspf"%>
  
        <h1>Detalhes Filme</h1>
        <hr />
        <form action="mvc?cmd=GeneroCmd&action=editado" method="post">
            <dl class="dl-horizontal">
                <dt>
                    <p><strong>ID:</strong></p>
                </dt>

                <dd>
                <td name="id"><c:out value="${filme.id}" /></td>
                </dd>
                <dt>
                    <p><strong>Titulo: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${filme.titulo}" /></td>
                </dd>
                <dt>
                    <p><strong> Diretor: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${filme.diretor}" /></td>
                </dd>
                <dt>
                    <p><strong> Ano de lançamento : </strong></p>
                </dt>

                <dd>
                <td><c:out value="${filme.anoEdicao}" /></td>
                </dd>
            
                  <dt>
                    <p><strong>  Genero: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${filme.genero.nome}" /></td>
                </dd>
                

            </dl>
            <hr />
            <p>
                <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
            </p>
        </form>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

   