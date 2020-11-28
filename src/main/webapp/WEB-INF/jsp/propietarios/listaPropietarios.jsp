<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="propietarios">
    <h2>Propietario</h2>

    <table id="propietariosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Telefono</th>
            <th>Gmail</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${propietarios}" var="propietario">
            <tr>
                <td>
                    <c:out value="${propietario.name}"/>
                </td>
                <td>
                    <c:out value="${propietario.apellido}"/>
                </td>
                <td>
                    <c:out value="${propietario.telefono}"/>
                </td>
                <td>
                   	<c:out value="${propietario.gmail}"/>
                </td>
                <td>
                   <spring:url value="/propietarios/delete/{propietarioId}" var="propietarioURL">
                   		  <spring:param name="propietarioId" value="${propietario.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(propietarioURL)}">Delete</a>
                </td>
                 <td>
           	    	<spring:url value="/propietarios/edit/{propietarioId}" var="propietarioEditUrl">
    		  			<spring:param name="propietarioId" value="${propietario.id}"/>
  					</spring:url>
                	<a href="${fn:escapeXml(propietarioEditUrl)}">Modify</a>
                </td>
             
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
