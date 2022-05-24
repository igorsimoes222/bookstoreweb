<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./contents/headerTags.jsp"/>
    </head>
    <body>
        <style>


            .tudo1{
                margin-top: 15%;
                width: 500px;
                height: 37px;
                color: black;
                text-align: center;
                text-decoration: underline 8px;
                box-shadow: 0px 0px 1000px 50px #87CEEB;

            }
            h2{

                border: 5px solid black; 


            }
            .tudao{
               margin-top: 15%;
               box-shadow: 1px 1px 10px 6px black; 
               max-width: min-content;
                
                
            }

        </style>
    <center>
        <jsp:include page="contents/headerTags.jsp"/>
        <div class="tudao">
            <h1 class="tudo1">Adicionar Livro</h1>

            <p><a href="<%=request.getContextPath()%>/bstore/new"> <span class="glyphicon glyphicon-plus"></span></a></p>
            <p><a href="<%=request.getContextPath()%>/bstore/list"><span class="glyphicon glyphicon-th-list"></span></a></p>

            <div align="center">
                <c:if test="${book != null}"><form action="update" method="post"></c:if>
                    <c:if test="${book == null}"><form action="insert" method="post"></c:if>
                            <table border="3" cellpadding="6">

                                <caption>
                                    <h2 class="tudo">
                                    <c:if test="${book != null}">Editar Livro</c:if>
                                    <c:if test="${book == null}"></c:if>
                                    </h2>

                                </caption>

                            <c:if test="${book != null}">
                                <input type="hidden" name="formId" value="<c:out value='${book.id}' />" />
                            </c:if>
                            <div class="tudo">
                                <tr class="tudo">
                                    <th>Titulo: </th>
                                    <td>
                                        <input type="text" name="formTitulo" size="45" value="<c:out value='${book.titulo}' />"/>
                                    </td>
                                </tr>
                                <tr class="tudo">
                                    <th>Autor: </th>
                                    <td><input type="text" name="formAutor" size="45" value="<c:out value='${book.autor}' />"/></td>
                                </tr>
                                <tr class="tudo">
                                    <th>Preco: </th>
                                    <td><input type="text" name="formPreco" size="5" value="<c:out value='${book.preco}' />"/></td>
                                </tr>
                                <tr class="tudo">
                                    <td colspan="2" align="center">
                                        <input type="submit" value="Enviar" />
                                    </td>
                                </tr>
                            </div>
                            </div>
                        </table>
                    </form>
            </div>
    </center>
</body>
</html>