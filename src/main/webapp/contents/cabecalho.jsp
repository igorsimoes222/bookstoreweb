<%-- 
    Document   : cabecalho
    Created on : 25 de abr de 2022, 13:50:47
    Author     : KGe
--%>
<style>
    .jumbotron{
        background-color: #87CEEB;
        font-family: arial,cursive;
        border: 2px solid black;
        font-size: 3.5em;
        text-decoration: underline whitesmoke 5px;
        box-shadow: 0px 0px 500px 20px;
    } 



</style>
<!-- Inicio cabecalho-->
<div class="jumbotron">BookStoreWeb</div>



<p>
    <a href="<%=request.getContextPath()%>/bstore/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>Adicionar novo Livro</a>

    <a href="<%=request.getContextPath()%>/bstore/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Lista todos Livros</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/login" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>Logout</a>

</p>

















<!-- Fim cabecalho-->