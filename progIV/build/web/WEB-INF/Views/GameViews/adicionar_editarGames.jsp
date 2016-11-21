<%-- 
    Document   : editarFilme
    Created on : 30/10/2016, 16:20:24
    Author     : geovane
--%>


<title>Adicionar/Editar jogo</title>
<!-- Bootstraps -->
<%@include file="/WEB-INF/jspf/head.jspf"%>

<div class="form-group"><h2><span class="glyphicon glyphicon-plus"></span> ${tAdEd} Jogo</h2></div>

<hr />
<form action="mvc?cmd=GameCmd&action=editado" method="post">
    <div class="row">


        <div class="form-group col-xs-3">
            <label for="id"> ID</label> 
            <input class="form-control" type="text" name="id" value="<c:out value="${game.id}" />" readonly="readonly" placeholder="Game ID" />
        </div>

        <div class="form-group col-xs-8">
            <label for="titulo">Titulo</label> 
            <input class="form-control" type="text"  required="required" name="titulo" value="<c:out value="${Game.titulo}" />" placeholder="Titulo" />
        </div>

    </div>

    <div class="row">
        <div class="form-group col-xs-3">
            <label for="ano">Lançameto</label> 
            <input class="form-control" type="number"  id = "ano" name="ano" value="<c:out value="${game.lancamento}" />" placeholder="Ano de Lançamento" />

        </div>

        <div class="form-group col-xs-6">
            <label for="comboRule">Genero:</label>
            <!-- COMBOBOX RULE -->
            <SELECT id="comboGeneros" name="comboGeneros" size="1" class="form-control">
                <c:forEach var="o" items="${comboGeneros}">
                    <option value="${o.id}" ${o.id == idSelecionado ? 'selected="selected"' : ''}>${o.nome}</option>
                </c:forEach>                                    
            </SELECT>
        </div>  




    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-success" >Salvar</button> | 
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </div>

</form>



<%@include file="/WEB-INF/jspf/footer.jspf"%>

