<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="proveedor">
    <h2>Proveedor</h2>

    <table id="proveedorTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Gmail</th>
            <th>Teléfono</th>
            <th>Acciones <th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${proveedor}" var="proveedor">
            <tr>
                <td>
                   
                 	<c:out value="${proveedor.name} "/>
                </td>
                <td>
                   
                 	<c:out value="${proveedor.gmail} "/>
                </td>
                <td>
                   
                 	<c:out value="${proveedor.telefono} "/>
                </td>
				<td>
                    <spring:url value="/proveedor/delete/{proveedorId}" var="proveedorUrl">
                    	<spring:param name="proveedorId" value="${proveedor.id}"/>
                    </spring:url>
                 	<a href="${fn:escapeXml(proveedorUrl)}"><span class="glyphicon glyphicon-trash" aria-hidden="true">&nbsp;</a>
           	    	<spring:url value="/proveedor/edit/{proveedorId}" var="proveedorEditUrl">
    		  			<spring:param name="proveedorId" value="${proveedor.id}"/>
  					</spring:url>
                	<a href="${fn:escapeXml(proveedorEditUrl)}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></a>
                </td>
              
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <form method="get" action="/proveedor/new">
      	<button class="btn btn-default" type="submit">Crear proveedor</button>
  		</form>
</petclinic:layout>
