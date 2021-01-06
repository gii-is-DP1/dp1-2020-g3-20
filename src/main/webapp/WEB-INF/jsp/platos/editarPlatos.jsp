<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="platos">
    <h2>Plato</h2>
    <form:form modelAttribute="plato" class="form-horizontal" id="add-plato-form"  action="/platos/edit">
        <div class="form-group has-feedback">
        <c:out value="${plato.id}"/> 
            <petclinic:inputField label="nombre" name="name"/>
            <petclinic:inputField label="precio" name="precio"/>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            <input type="text" name="id" value="${plato.id}"> 
            
                  
            <button class="btn btn-default" type="submit">Save</button>
                  
            </div>
        </div>
    </form:form>
</petclinic:layout>
