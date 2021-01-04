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
            <th>Fecha Caducidad</th>
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
                    <c:out value="${producto.fechaCaducidad}"/>
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
                   	<c:out value="${producto.proveedor}"/>
                </td>
                <td>
                   <spring:url value="/producto/edit/{productoId}" var="productoURL">
                   		  <spring:param name="productoId" value="${producto.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(productoURL)}">Editar</a>
                   
                   <spring:url value="/producto/delete/{productoId}" var="productoURL">
                   		  <spring:param name="productoId" value="${producto.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(productoURL)}">Eliminar</a>
                   
                   <spring:url value="/producto/savePedido/{productoId}" var="productoURL">
                   		  <spring:param name="productoId" value="${producto.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(productoURL)}">Pedir</a>          
                </td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
