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
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Pedido</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lineaPedido}" var="lineaPedido">
            <tr>
                <td>
                    <c:out value="${lineaPedido.producto.name}"/>
                </td>
                <td>
                    <c:out value="${lineaPedido.cantidad}"/>
                </td>
                <td>
                   	<c:out value="${lineaPedido.pedido.id}"/>
                </td>
            </tr>
        </c:forEach>
         
        </tbody>
    </table>
    <form method="get" action="/lineaPedido/new">
      	<button class="btn btn-default" type="submit">New</button>
  	</form>

</petclinic:layout>
