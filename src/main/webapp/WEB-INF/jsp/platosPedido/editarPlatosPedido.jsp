<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platopedido">
    <h2>platopedido</h2>
    <form:form modelAttribute="platopedido" class="form-horizontal" id="add-platopedido-form" action="/platopedido/edit">
        <div class="form-group has-feedback">
            <div class="control-group">
				<petclinic:selectField name="estadoplatodto" label="estado" names="${estadosPlato}" size="6"/>
            </div>
             <div class="control-group">
				<petclinic:selectField name="platodto" label="platos" names="${listaPlatos}" size="6"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${platopedido.id}">  
                <div>
                <button class="btn btn-default" type="submit">Anadir plato pedido</button>
                </div>
            </div>
        </div>
    </form:form>
</petclinic:layout>
