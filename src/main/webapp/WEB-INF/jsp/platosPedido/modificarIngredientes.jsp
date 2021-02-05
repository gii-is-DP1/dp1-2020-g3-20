<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platopedido">

    <h2><c:out value="${plato.name}"/></h2>

    <h2>Ingredientes:</h2>
	
	<form modelAttribute="editIngredientesP" class="form-horizontal" id="add-platopedido-form" action="/platopedido/edit">
    <table class="table table-striped">
        <c:forEach var="ingrediente" items="${listaIngredientes}">
            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt><c:out value="${ingrediente.producto.name}"/></dt>
                        <dd><input type="text" id="fname" name="fname" value="${ingrediente.cantidadUsualPP}"></dd>
                    </dl>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>

</petclinic:layout>
