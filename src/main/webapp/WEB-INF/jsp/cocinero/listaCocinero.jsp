<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cocinero">
    <h2>Cocinero</h2>

    <table id="cocineroTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Telefono</th>
            <th>Gmail</th>
            <th>Usuario</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cocinero}" var="cocinero">
            <tr>
                <td>
                    <c:out value="${cocinero.name}"/>
                </td>
                <td>
                    <c:out value="${cocinero.apellido}"/>
                </td>
                <td>
                    <c:out value="${cocinero.telefono}"/>
                </td>
                <td>
                   	<c:out value="${cocinero.gmail}"/>
                </td>
                <td>
                   	<c:out value="${cocinero.usuario}"/>
                </td>
                <td>
                   	<spring:url value="/cocinero/delete/{cocineroId}" var="cocineroURL">
                    	<spring:param name="cocineroId" value="${cocinero.id}"/>
                   	</spring:url>
                   	<a href="${fn:escapeXml(cocineroURL)}">Eliminar</a>
                   
                   	<spring:url value="/cocinero/edit/{cocineroId}" var="cocineroEditUrl">
    		  	   		<spring:param name="cocineroId" value="${cocinero.id}"/>
  					</spring:url>
                	<a href="${fn:escapeXml(cocineroEditUrl)}">Editar</a>
                </td>              
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <form method="get" action="/cocinero/new">
      	<button class="btn btn-default" type="submit">crear cocinero</button>
  		</form>
</petclinic:layout>
