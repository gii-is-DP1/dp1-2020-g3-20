<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platos">
    <h2>Nuevo ingrediente</h2>
     
    <form:form modelAttribute="ingredienteaux" class="form-horizontal" id="add-ingrediente-form" action="/platos/ingSave">
   
        <div class="form-group has-feedback">
        	<h2>Plato <c:out value="${plato.name }"></c:out></h2>
            <div class="control-group">
				<petclinic:selectField name="producto" label="productos" names="${listaProductos}" size="6"/>
            </div>
            <petclinic:inputField label="cantidad" name="cantidadUsualPP"></petclinic:inputField>
            
            <input type="hidden" name="id" value="${ingrediente.id}">
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
          
               	
                <button class="btn btn-default" type="submit">Anadir ingrediente</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
