<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cocinero">
    <h2>Editar Cocinero</h2>
    <form:form modelAttribute="cocinero" class="form-horizontal" id="edit-cocinero-form"  action="/cocinero/edit">
        <div class="form-group has-feedback">
           <petclinic:inputField label="nombre" name="name"/>
           <petclinic:inputField label="apellido" name="apellido"/>
           <petclinic:inputField label="gmail" name="gmail"/>
           <petclinic:inputField label="telefono" name="telefono"/>
           <petclinic:inputField label="usuario" name="usuario"/>
           <petclinic:inputField label="contrasena" name="contrasena"/>
            
        </div>
      <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${cocinero.id}">  
                <button class="btn btn-default" type="submit">Editar cocinero</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
