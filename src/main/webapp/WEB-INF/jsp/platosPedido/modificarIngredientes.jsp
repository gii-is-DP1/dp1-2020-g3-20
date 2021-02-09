<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="platopedido">

	<h2>
		<c:out value="${plato.name}" />
	</h2>

	<h2>Ingredientes:</h2>
	<c:forEach items="${ingredientespedido}" var="ingredientePedido">
		<div>
			<div class="btn-line">
				<c:out value="${ingredientePedido.ingrediente.producto.name}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<form:form modelAttribute="ingredientePedido" class="btn-line"
				id="add-ingrediente-form"
				action="/platopedido/${comandaId}/guardarIngrediente/${ingredientePedido.pp.id}/${ingredientePedido.ingrediente.id}">


				<input type="hidden" id="cantidadPedida" name="cantidadPedida"
					value="${ingredientePedido.cantidadPedida}" />
				<%--	<petclinic:inputField label="cantidadPedida" name="cantidadPedida" value="${ingredientePedido.cantidadPedida}"/> --%>

				<button class="btn btn-default" type="submit">Anadir Ingrediente</button>
			</form:form>
		</div>
	</c:forEach>
	<br>
	<br>
	<form:form modelAttribute="ingredientePedido" class="form-horizontal"
		id="add-comanda-form"
		action="/comanda/listaComandaActual/asignar/${comandaId}/${platopedidoId}">
		<button class="btn btn-default" type="submit">Finalizar y
			asignar a comanda</button>
	</form:form>

	<%-- 
	<spring:url
		value="comanda/listaComandaActual/asignar/{comandaId}/{ppId}"
		var="platopedidoURL">
		<spring:param name="comandaId" value="${comandaId}" />
		<spring:param name="ppId" value="${platopedido.id}" />
	</spring:url>
	<a href="${fn:escapeXml(platopedidoURL)}">Finalizar y asignar a
		comanda</a>--%>
</petclinic:layout>