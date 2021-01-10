<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platopedido">
    <h2>PlatoPedido</h2>

    <table id="ppTable" class="table table-striped">
        <thead>
        <tr>
   			<th>Estado del plato</th>
   			<th>Id del plato asociado</th>		
   			<th>Acciones</th>	
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${platopedido}" var="platopedido">
            <tr>
                <td>
                    <c:out value="${platopedido.estadoplato}"/>
                </td>
                   <td>
                    <c:out value="${platopedido.plato.name}"/>
                </td>
                
                <td>
                   <spring:url value="/platopedido/edit/{ppId}" var="ppURL">
                   		  <spring:param name="ppId" value="${platopedido.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(ppURL)}">Editar</a>
                   <spring:url value="/platopedido/delete/{ppId}" var="ppURL">
                   		  <spring:param name="ppId" value="${platopedido.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(ppURL)}">Eliminar</a>
                   
                   <spring:url value="/platopedido/{ppId}" var="ppURL">
                   		  <spring:param name="ppId" value="${platopedido.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(ppURL)}">Ingredientes</a>
                </td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
