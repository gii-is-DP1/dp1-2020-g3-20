<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Historial de comandas">
    <h2>Comandas</h2>

    <table id="comandaTable" class="table table-striped">
        <thead>
        <tr> 
            <th>Mesa</th>
            <th>Fecha pedido</th>
            <th>Camarero</th>
            <th>Precio total</th>
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
