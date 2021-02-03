<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="lineaPedido">
    <h2>Comandas</h2>
    <table class="table table-striped">
        <c:forEach var="comanda" items="${comanda}">
            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Plato</dt>
                        <dd><c:out value="${comanda.}"/></dd>
                        
                        <dt>Estado</dt>
                        <dd><c:out value="${ing.cantidadUsualPP}"/></dd>
                    </dl>
                </td>
            </tr>
        </c:forEach>
    </table>
</petclinic:layout>
