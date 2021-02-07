<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="platopedido">
	<h1>PlatoPedido</h1>
	<div class="ppContainer">
		<div class="tabla1">
			<h2>
				<c:out value="En proceso"></c:out>
			</h2>
			<table id="ppTable" class="table table-striped">
				<thead>
					<tr>
						<th>Nombre del plato</th>
						<th>Mesa</th>
						<th>Cambiar Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${platopedido}" var="platopedido">
						<c:choose>
							<c:when test="${platopedido.estadoplato == 'ENPROCESO'}">
								<tr>
									<td><c:out value="${platopedido.plato.name}" /></td>
									<td><c:out value="${platopedido.comanda.mesa}" /></td>
									<td><spring:url
											value="/platopedido/modificarEstado/{platopedidoID}/{cambiarA}"
											var="platopedidoURL">
											<spring:param name="platopedidoID" value="${platopedido.id}" />
											<spring:param name="cambiarA" value="FINALIZADO" />
										</spring:url>
										<form action="${fn:escapeXml(platopedidoURL)}">
											<button class="btn btn-default" type="submit">Finalizar</button>
										</form></td>
									<td><spring:url value="/platopedido/{ppId}" var="ppURL">
											<spring:param name="ppId" value="${platopedido.id}" />
										</spring:url>
										<form action="${fn:escapeXml(ppURL)}">
											<button class="btn btn-default" type="submit">Ingredientes</button>
										</form></td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="tabla2">
			<h2>
				<c:out value="En cola"></c:out>
			</h2>
			<table id="ppTable" class="table table-striped">
				<thead>
					<tr>
						<th>Nombre del plato</th>
						<th>Mesa</th>
						<th>Cambiar Estado</th>
						<th>Acciones</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${platopedido}" var="platopedido">
						<c:choose>
							<c:when test="${platopedido.estadoplato == 'ENCOLA'}">
								<tr>
									<td><c:out value="${platopedido.plato.name}" /></td>
									<td><c:out value="${platopedido.comanda.mesa}" /></td>
									<td><spring:url
											value="/platopedido/modificarEstado/{platopedidoID}/{cambiarA}"
											var="platopedidoURL">
											<spring:param name="platopedidoID" value="${platopedido.id}" />
											<spring:param name="cambiarA" value="ENPROCESO" />
										</spring:url>
										<form action="${fn:escapeXml(platopedidoURL)}">
											<button class="btn btn-default" type="submit">Comenzar
												Plato</button>
										</form></td>
									<td><spring:url value="/platopedido/{ppId}" var="ppURL">
											<spring:param name="ppId" value="${platopedido.id}" />
										</spring:url>
										<form action="${fn:escapeXml(ppURL)}">
											<button class="btn btn-default" type="submit">Ingredientes</button>
										</form></td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</petclinic:layout>
