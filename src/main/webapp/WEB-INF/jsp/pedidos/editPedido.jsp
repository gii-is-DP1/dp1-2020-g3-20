<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pedidos">
	<jsp:attribute name="customScript">
		<script>
			$(function (){
				$("#fechaPedido").datepicker({dateFormat: 'yy/mm/dd'});
				$("#fechaEntrega").datepicker({dateFormat: 'yy/mm/dd'});
			}); 
		
		</script>
	</jsp:attribute>
	<jsp:body>
		<h2>Pedido</h2>
    	<form:form modelAttribute="pedido" class="form-horizontal" id="add-pedido-form" action="/pedidos/save">
        	<div class="form-group has-feedback">
            	<petclinic:inputField label="costeTotal" name="costeTotal"/>
            	<petclinic:inputField label="proveedor" name="proveedor"/>
            	<%-- <div class="control-group">
                    <petclinic:selectField name="proveedor" label="proveedor " names="${proveedor}" size="5"/>
                </div>--%>
        	</div>
        	<div class="form-group">
            	<div class="col-sm-offset-2 col-sm-10">
                	<input type="hidden" name="id" value="${pedido.id}">  
                	<button class="btn btn-default" type="submit">Anadir Pedido</button>
            	</div>
        	</div>
    	</form:form>
	</jsp:body>
    
</petclinic:layout>

