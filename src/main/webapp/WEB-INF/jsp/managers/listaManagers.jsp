<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="managers">
    <h2>Manager</h2>

    <table id="managersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Telefono</th>
            <th>Gmail</th>
            <th>Usuario</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${managers}" var="manager">
            <tr>
                <td>
                    <c:out value="${manager.name}"/>
                </td>
                <td>
                    <c:out value="${manager.apellido}"/>
                </td>
                <td>
                    <c:out value="${manager.telefono}"/>
                </td>
                <td>
                   	<c:out value="${manager.gmail}"/>
                </td>
                <td>
                   	<c:out value="${manager.usuario}"/>
                </td>
                <td>
                   <spring:url value="/managers/delete/{managerId}" var="managerURL">
                   		  <spring:param name="managerId" value="${manager.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(managerURL)}">Delete</a>
               
           	    	<spring:url value="/managers/edit/{managerId}" var="managerEditUrl">
    		  			<spring:param name="managerId" value="${manager.id}"/>
  					</spring:url>
                	<a href="${fn:escapeXml(managerEditUrl)}">Modify</a>
                </td>

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
