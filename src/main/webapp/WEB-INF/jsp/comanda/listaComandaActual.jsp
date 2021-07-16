<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<script>
function validar(){
	var mesa = document.forms["formularioComanda"]["mesa"].value;
	
	if(mesa == null){
        alert("Inserta un n�mero de mesa");
        return false;
    }
	
	if(mesa < 1 || mesa > 20){
        alert("El n�mero de la mesa debe estar entre 1 y 20");
        return false;
    }
	
	return true;
} 
</script>

<petclinic:layout pageName="Comanda">
	<h2>Comandas</h2>
	
	<spring:url value="/comanda/listaComandaActual/new" var="newURL">
	</spring:url>
	<form name="formularioComanda" class="btn-line" action="${fn:escapeXml(newURL)}" onsubmit="return validar();">
		<p align="right">
			<input name="mesa" type="number">
			<button class="btn btn-default" type="submit">Nueva comanda</button>
		</p>
	</form>
 	 
	<table id="comandaActualTable" class="table table-striped">
		<thead>
			<tr>
				<th>Mesa</th>
				<th>Camarero</th>
				<th>Precio total</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comanda}" var="comanda">
				<tr>
					<td><c:out value="${comanda.mesa}" /></td>
					<td><c:out value="${comanda.camarero.name}" /></td>
					<td><c:out value="${comanda.precioTotal} " /><span
						class="glyphicon glyphicon-euro" aria-hidden="true"></span></td>
					<td><spring:url
							value="/comanda/listaComandaActual/{comandaID}" var="infoURL">
							<spring:param name="comandaID" value="${comanda.id}" />
						</spring:url>
						<form class="btn-line" action="${fn:escapeXml(infoURL)}">
							<button class="btn btn-default" type="submit">Info</button>
						</form> <spring:url
							value="/comanda/listaComandaActual/finalizarComanda/{comandaID}"
							var="closeURL">
							<spring:param name="comandaID" value="${comanda.id}" />
						</spring:url>
						<form class="btn-line" action="${fn:escapeXml(closeURL)}">
							<button class="btn btn-default" type="submit">Finalizar
								comanda</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>
