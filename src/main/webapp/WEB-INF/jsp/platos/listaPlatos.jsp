<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platos">
    <h2>Plato</h2>

    <table id="camarerosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${platos}" var="plato">
            <tr>
                <td>
                    <c:out value="${plato.name}"/>
                </td>
                <td>
                    <c:out value="${plato.precio}"/> euros
                </td>
              
                <td>
                   <spring:url value="/platos/delete/{platoId}" var="platoURL">
                   		  <spring:param name="platoId" value="${plato.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(platoURL)}">Delete</a>
               
                <spring:url value="/platos/edit/{platoId}" var="platoEditUrl">
    		  			<spring:param name="platoId" value="${plato.id}"/>
  					</spring:url>
                	<a href="${fn:escapeXml(platoEditUrl)}">Modify</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>