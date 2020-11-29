<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="camareros">
    <h2>Camarero</h2>

    <table id="camarerosTable" class="table table-striped">
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
        <c:forEach items="${camareros}" var="camarero">
            <tr>
                <td>
                    <c:out value="${camarero.name}"/>
                </td>
                <td>
                    <c:out value="${camarero.apellido}"/>
                </td>
                <td>
                    <c:out value="${camarero.telefono}"/>
                </td>
                <td>
                   	<c:out value="${camarero.gmail}"/>
                </td>
                <td>
                   <spring:url value="/camareros/delete/{camareroId}" var="camareroURL">
                   		  <spring:param name="camareroId" value="${camarero.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(camareroURL)}">Delete</a>
                   
                    <spring:url value="/camareros/edit/{camareroId}" var="camareroEditUrl">
    		  			<spring:param name="camareroId" value="${camarero.id}"/>
  					</spring:url>
                	<a href="${fn:escapeXml(camareroEditUrl)}">Modify</a>
                </td>
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
