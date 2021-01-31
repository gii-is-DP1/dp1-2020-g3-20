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
   			<th>Nombre del plato</th>		
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
                   <a href="${fn:escapeXml(ppURL)}">Eliminar</a>
                   
                   <spring:url value="/platopedido/{ppId}" var="ppURL">
                   		  <spring:param name="ppId" value="${platopedido.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(ppURL)}">Ingredientes</a>
                </td> 
                <td>
					<c:choose>
  						<c:when test="${platopedido.estadoplato == 'ENPROCESO'}">
  						<spring:url value="/platopedido/modificarEstado/{platopedidoID}/{cambiarA}" var="paltopedidoURL">
                   		  	<spring:param name="paltopedidoID" value="${platopedido.id}"/>
                   		  	<spring:param name="cambiarA" value="FINALIZADO"/>
                   			</spring:url>
                   			<a href="${fn:escapeXml(platopedidoURL)}">Finalizar Plato</a>
 					   	</c:when>
					</c:choose>
					<c:choose>
  						<c:when test="${platopedido.estadoplato == 'ENCOLA'}">
  						<spring:url value="/platopedido/modificarEstado/{platopedidoID}/{cambiarA}" var="paltopedidoURL">
                   		  	<spring:param name="paltopedidoID" value="${platopedido.id}"/>
                   		  	<spring:param name="cambiarA" value="ENPROCESO"/>
                   		  	</spring:url>
                   			<a href="${fn:escapeXml(platopedidoURL)}">Cambiar a EnProceso</a>
 					   	</c:when>
					</c:choose>
  				</td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
