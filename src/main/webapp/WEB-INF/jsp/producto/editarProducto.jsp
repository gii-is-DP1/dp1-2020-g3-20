<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="producto">
    <h2>Editar Producto</h2>
    <form:form modelAttribute="producto" class="form-horizontal" id="edit-producto-form"  action="/producto/edit">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="name"/>
            <div class="control-group">
				<petclinic:selectField name="tipoproductodto" label="Tipo Producto " names="${listaTipos}" size="6"/>
            </div>
            <petclinic:inputField label="Cantidad minima" name="cantMin"/>
            <petclinic:inputField label="Cantidad actual" name="cantAct"/>
            <petclinic:inputField label="Cantidad maxima" name="cantMax"/>
            <div class="control-group">
				<petclinic:selectField name="proveedor" label="Proveedor " names="${listaProveedores}" size="6"/>
            </div>
        </div>
      <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${producto.id}">  
                <button class="btn btn-default" type="submit">Actualizar producto</button>
                  
            </div>
        </div>
    </form:form>
</petclinic:layout>
