<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="producto">
    <h2>Producto</h2>

    <table id="productoTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Tipo Producto</th>
            <th>Cantidad Mínima</th>
            <th>Cantidad Actual</th>
            <th>Cantidad Máxima</th>
            <th>Proveedor</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${producto}" var="producto">
            <tr>
                <td>
                    <c:out value="${producto.name}"/>
                </td>
                <td>
                    <c:out value="${producto.tipoProducto}"/>
                </td>
                <td>
                    <c:out value="${producto.cantMin}"/>
                </td>
                <td>
                    <c:out value="${producto.cantAct}"/>
                </td>
                <td>
                   	<c:out value="${producto.cantMax}"/>
                </td>
                <td>
                   	<c:out value="${producto.proveedor.name}"/>
                </td>
                <td>
                   <spring:url value="/producto/edit/{productoId}" var="productoURL">
                   		  <spring:param name="productoId" value="${producto.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(productoURL)}">Editar</a>
                   
                   <spring:url value="/producto/delete/{productoId}" var="productoURL">
                   		  <spring:param name="productoId" value="${producto.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(productoURL)}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></a>
                   
                   <spring:url value="/producto/savePedido/{productoId}" var="productoURL">
                   		  <spring:param name="productoId" value="${producto.id}"/>
                   </spring:url>
                   
                   <c:choose>
  						<c:when test="${producto.cantMin >= producto.cantAct}">
                			<a href="${fn:escapeXml(productoURL)}">Pedir</a>  
 				   		</c:when>
					</c:choose> 
                </td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="get" action="/producto/new">
      	<button class="btn btn-default" type="submit">Crear Producto</button>
  	</form>
</petclinic:layout>
