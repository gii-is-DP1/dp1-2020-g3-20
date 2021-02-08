<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<script>
function validarsize(){
	var name = document.forms["manager"]["name"].value;
	var apellido = document.forms["manager"]["apellido"].value;
	var gmail = document.forms["manager"]["gmail"].value;
	var usuario = document.forms["manager"]["usuario"].value;
	var contrasena = document.forms["manager"]["contrasena"].value;
	if(name.length < 3 || name.length > 75){
        alert("La longitud del nombre debe ser mayor de 3 o menor de 75");
        return false;
    }
	if(apellido.length < 3 || apellido.length > 75){
        alert("La longitud del apellido debe ser mayor de 3 o menor de 75");
        return false;
    }
	if(gmail.length < 3 || gmail.length > 75){
        alert("La longitud del gmail debe ser mayor de 3 o menor de 75");
        return false;
    }
	if(usuario.length < 3 || usuario.length > 75){
        alert("La longitud del usuario debe ser mayor de 3 o menor de 75");
        return false;
    }
	if(contrasena.length < 3 || contrasena.length > 75){
        alert("La longitud del contrasena debe ser mayor de 3 o menor de 75");
        return false;
    }
	return true;
} 
</script>
<petclinic:layout pageName="managers">
	<h2>Manager</h2>
	<form:form name="manager" modelAttribute="manager" class="form-horizontal" id="add-manager-form" action="/managers/edit" onsubmit="return validarsize();">
		<div class="form-group has-feedback">
			<petclinic:inputField label="nombre" name="name" />
			<petclinic:inputField label="apellido" name="apellido" />
			<petclinic:inputField label="gmail" name="gmail" />
			<petclinic:inputField label="telefono" name="telefono" />
			<petclinic:inputField label="usuario" name="usuario" />
			<petclinic:inputField label="contrasena" name="contrasena" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="id" value="${manager.id}">
				<button class="btn btn-default" type="submit">Actualizar</button>
			</div>
		</div>
		<input type="hidden" name="version" value="${manager.version}" />
	</form:form>
</petclinic:layout>
