<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Historial de comandas">
    <h2>Comanda</h2>
    <table id="comandaExistenteTable" class="table table-striped">

        <thead>
        <tr>
            <th>Plato</th>
            <th>Fecha creación</th>
            <th>Fecha finalización</th>
            <th>Camarero</th>
            <th>Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comanda}" var="comanda">
            <tr>
                <td>
                    <c:out value="${comanda.mesa}"/>
                </td>
                <td>
                    <c:out value="${comanda.fechaCreado.dayOfMonth}/${comanda.fechaCreado.monthValue}/${comanda.fechaCreado.year} - ${comanda.fechaCreado.hour}:${comanda.fechaCreado.minute}"/>
                </td>
                <td>
                	<c:choose>
  						<c:when test="${comanda.fechaFinalizado == null}">
 							<c:out value="Comanda abierta"/>
 				   		</c:when>
 				   		<c:when test="${comanda.fechaFinalizado != null}">
 							<c:out value="${comanda.fechaFinalizado.dayOfMonth}/${comanda.fechaFinalizado.monthValue}/${comanda.fechaFinalizado.year} - ${comanda.fechaFinalizado.hour}:${comanda.fechaFinalizado.minute}"/>
 				   		</c:when>
					</c:choose>
                
                    
                </td>
                <td>
                   	<c:out value="${comanda.camarero.name}"/>
                </td>
                <td>
                   	<c:out value="${comanda.precioTotal} "/><span class="glyphicon glyphicon-euro" aria-hidden="true"></span>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
	<form method="get" action="/comanda/listaComandaTotal/dia">
  		<input name="date" type="date"> 
      	<button class="btn btn-default" type="submit">Buscar</button>
  	</form>
</petclinic:layout>
