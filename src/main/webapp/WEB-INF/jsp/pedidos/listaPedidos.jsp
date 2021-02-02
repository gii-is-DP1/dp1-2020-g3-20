<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="pedidos">
    <h2>Pedido</h2>

    <table id="pedidosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha De Realizacion</th>
            <th>Fecha De Entrega</th>
            <th>¿Ha Llegado?</th>
            <th>Proveedor</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pedido}" var="pedido">
            <tr>
                <td>
                    <c:out value="${pedido.fechaPedido}"/>
                </td>
                <td>
                    <c:out value="${pedido.fechaEntrega}"/>
                </td>
                <td>
                   	<c:out value="${pedido.haLlegado ? 'Sí' : 'No'}"/>
                </td>
                <td>
                   	<c:out value="${pedido.proveedor.name}"/>
                </td> 
                <td>
                	<form method="get" action="/lineaPedido/porPedido">
  						<input name="pedidoID" type="hidden" value="${pedido.id}"> 
      					<button class="btn btn-default" type="submit">Info</button>
  					</form>
               		<c:choose>
  						<c:when test="${!pedido.haLlegado}">
    					   	<spring:url value="/pedidos/terminarPedido/{pedidoID}" var="pedidoURL">
                	  		<spring:param name="pedidoID" value="${pedido.id}"/>
                			</spring:url>
                			<a href="${fn:escapeXml(pedidoURL)}">Finalizar Pedido</a>
 				   		</c:when>
					</c:choose>
  				</td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>


  	<form method="get" action="/pedidos/porProveedor">
  		<input name="proveedorID" > 
      	<button class="btn btn-default" type="submit">Buscar</button>
  	</form>
  	<%--   <form method="get" action="/lineaPedido">
      	<button class="btn btn-default" type="submit">Todas las lineas pedido</button>
  	</form> --%>
    
</petclinic:layout>
