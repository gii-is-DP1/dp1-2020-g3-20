<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platopedido">

    <h2><c:out value="${plato.name}"/></h2>

    <h2>Ingredientes:</h2>
	<c:forEach items="${ingredientespedido}" var="ingredientePedido">
		<c:out value="${ingredientePedido.pp.id}" />
		<c:out value="${ingredientePedido.ingrediente.producto.name}" />
			<form:form modelAttribute="ingredientePedido" class="form-horizontal" id="add-ingrediente-form" action="/platopedido/guardarIngrediente/${ingredientePedido.pp.id}/${ingredientePedido.ingrediente.id}">
	        <div class="form-group has-feedback">
	            <div class="control-group">
	            	<input type="number" id="cantidadPedida" name="cantidadPedida" value="${ingredientePedido.cantidadPedida}"/>
	            <%--	<petclinic:inputField label="cantidadPedida" name="cantidadPedida" value="${ingredientePedido.cantidadPedida}"/> --%>
	            </div>
	        </div>
	        <div class="form-group">
	            <div class="col-sm-offset-2 col-sm-10">  
	                <div> 
	                <button class="btn btn-default" type="submit">Añadir Ingrediente</button>
	                </div>
	            </div>
	        </div>
	    </form:form>
	</c:forEach>
	<spring:url value="/platopedido/modificarComanda/{comandaId}" var="platopedidoURL">
    <spring:param name="comandaId" value="${platopedido.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(platopedidoURL)}">Finalizar y asignar a comanda</a>
	
	
<%-- 	<form:form modelAttribute="listaIngredientes" class="form-horizontal" id="add-platopedido-form" action="/platopedido/guardarIngredientes">
    
        <c:forEach var="ingrediente" items="${listaIngredientes}">
            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt><c:out value="${ingrediente.producto.name}"/></dt>
                        <dd><input type="number" id="fname" name="fname" value="${ingrediente.cantidadUsualPP}"></dd>
                    </dl>
                </td>
            </tr>
            </c:forEach>
           	<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${platopedido.id}">  
                <div>
                <button class="btn btn-default" type="submit">Anadir plato pedido</button>
                </div>
            </div>
			</div>
        
    </table>
    </form:form> 
	
	<form:form modelAttribute="ingredientespedido" class="form-horizontal" id="add-platopedido-form" action="/platopedido/guardarIngredientes">
        <div class="form-group has-feedback">
            <div class="control-group">
				<c:forEach var="ingrediente" items="${ingredientespedido}">
            		<input type="number" id="ingrediente" name="fname" value="${ingrediente.cantidadPedida}">
            	</c:forEach>
				
            </div>
          
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">  
                <div>
                <button class="btn btn-default" type="submit">Anadir plato pedido y asignar a comanda</button>
                </div>
            </div>
        </div>
    </form:form>--%>
</petclinic:layout>
