<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="Info comanda">
	<h2>Comanda mesa <c:out value="${comanda.mesa}"></c:out></h2>
	<div class="comandaContainer">
		<div class="tabla1">
			<table id="editarComandaTable" class="table table-striped">
				<thead>
					<tr>
						<th>Plato</th>
						<th>Estado del plato</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${platop}" var="platop">
						<tr>
							<td><c:out value="${platop.plato.name}" /></td>
							<td><c:out value="${platop.estadoplato}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="tabla2">
				<petclinic:selectField name="listaPlatos" label="Lista de platos:" names="${listaPlatos}" size="10" />
		</div>
	</div>
</petclinic:layout>
