<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="menus">
    <h2>Menu</h2>
    <form:form modelAttribute="menu" class="form-horizontal" id="add-menu-form" action="/menus/save">
        <div class="form-group has-feedback">
            <petclinic:inputField label="nombre" name="name"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${menu.id}">  
                <button class="btn btn-default" type="submit">A�adir menu</button>
                  
            </div>
        </div>
    </form:form>
</petclinic:layout>

