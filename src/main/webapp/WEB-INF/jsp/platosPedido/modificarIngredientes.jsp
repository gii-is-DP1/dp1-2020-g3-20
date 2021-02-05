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
		<c:out value="${ingredientePedido.pp.id}" />
		<c:out value="${ingredientePedido.ingrediente.producto.name}" />
		<form:form modelAttribute="ingredientePedido" class="form-horizontal"
			id="add-ingrediente-form"
			action="/platopedido/guardarIngrediente/${ingredientePedido.pp.id}/${ingredientePedido.ingrediente.id}">
			<div class="form-group has-feedback">
				<div class="control-group">
					<input type="number" id="cantidadPedida" name="cantidadPedida"
						value="${ingredientePedido.cantidadPedida}" />
					<%--	<petclinic:inputField label="cantidadPedida" name="cantidadPedida" value="${ingredientePedido.cantidadPedida}"/> --%>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div>
						<button class="btn btn-default" type="submit">Añadir
							Ingrediente</button>
					</div>
				</div>
			</div>
		</form:form>
	</c:forEach>
	<spring:url value="/platopedido/modificarComanda/{comandaId}"
		var="platopedidoURL">
		<spring:param name="comandaId" value="${platopedido.id}" />
	</spring:url>
	<a href="${fn:escapeXml(platopedidoURL)}">Finalizar y asignar a
		comanda</a>
</petclinic:layout>