<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="lineaPedido">
    <h2>LineaPedido</h2>

    <table id="lineaPedidoTable" class="table table-striped">
        <thead>
        <tr>
            <th>Mesa</th>
            <th>Fecha pedido</th>
            <th>Camarero</th>
            <th>Precio total</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comanda}" var="comanda">
            <tr>
                <td>
                    <c:out value="${comanda.mesa}"/>
                </td>
                <td>
                    <c:out value="${comanda.mesa}"/>
                </td>
                <td>
                   	<c:out value="${comanda.mesa}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="get" action="/lineaPedido/new">
      	<button class="btn btn-default" type="submit">New</button>
  	</form>
	<form method="get" action="/lineaPedido/porPedido">
  		<input name="pedidoID" value="${pedido.id}"> 
      	<button class="btn btn-default" type="submit">Buscar</button>
  	</form>
    
</petclinic:layout>