<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platopedido">

    <h2>Plato <c:out value="${platopedido.plato.name}"/></h2>

  
   <!-- 
    <spring:url value="{platoId}/ingrediente/new" var="addUrl">
        <spring:param name="platoId" value="${plato.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Agregar Ingrediente</a>
	-->
    <br/>
    <br/>
    <br/>
    <h2>Ingredientes:</h2>
	

    <table class="table table-striped">
        <c:forEach var="ing" items="${ingredientespedido}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${ing.ingrediente.producto.name}"/></dd>
                        
                        <dt>Cantidad</dt>
                        <dd><c:out value="${ing.cantidadPedida}"/></dd>
                    </dl>
                </td>
                <!-- 
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                           
                            <th>Description</th>
                        </tr>
                        </thead>
                       
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}">Edit Pet</a>
                            </td>
                         
                        </tr>
                    </table>
                </td> -->
            </tr>

        </c:forEach>
    </table>

</petclinic:layout>
