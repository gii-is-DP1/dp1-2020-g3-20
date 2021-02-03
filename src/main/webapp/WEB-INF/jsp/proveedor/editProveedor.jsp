<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="proveedores">
    <h2>Proveedor</h2>
    <form:form modelAttribute="proveedor" class="form-horizontal" id="add-proveedor-form" action="/proveedor/save">
        <div class="form-group has-feedback">
            <petclinic:inputField label="nombre" name="name"/>
            <petclinic:inputField label="gmail" name="gmail"/>
            <petclinic:inputField label="telefono" name="telefono"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${proveedor.id}">  
                <button class="btn btn-default" type="submit">Anadir proveedor</button>
                  
            </div>
        </div>
    </form:form>
</petclinic:layout>
