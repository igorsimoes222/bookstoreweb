<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="contents/headerTags.jsp"/>
        <title>Aplicação</title>
    </head>
    <body >
        <style>
            body{
                background-color: darkgrey;



            }
            h2{
                border: 2px solid black; 
                color: black;
                font-family: monospace;
                font-size: 4em;

            }
            .container{
                color: black; 

            }
            .table{
                background-color: whitesmoke;

            }
            .ids{
                border: 2px solid black; 
            }






        </style>
    <center>
        <div class="container">

            <jsp:include page="contents/cabecalho.jsp"/>
            <div class="table responsive">
                <table class="table table-hover">
                    <h2>List of Books</h2>
                    <tr class="ids">

                        <th class="ids">ID</th>
                        <th class="ids">Titulo</th>
                        <th class="ids">Autor</th>
                        <th class="ids">Preco</th>
                        <th class="ids">Ações</th>

                    </tr>

                    <c:forEach var="book" items="${listaBook}">
                        <tr class="ids">
                            <td><c:out value="${book.id}" /></td>
                            <td><c:out value="${book.titulo}" /></td>
                            <td><c:out value="${book.autor}" /></td>
                            <td><c:out value="${book.preco}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/bstore/edit?id=<c:out value='${book.id}' />"><span class="glyphicon glyphicon-pencil"></span> </a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="<%=request.getContextPath()%>/bstore/delete?id=<c:out value='${book.id}'/>"><span class="glyphicon glyphicon-trash"></span> </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="contents/rodape.jsp"/>
        </div>
    </center>
</body>
</html>
