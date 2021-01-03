<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">


    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/owners/new" htmlEscape="true"/>'>Add Owner</a>
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
	<H1>bienvenido a foorder, por favor identifícate y disfruta de la aplicación
	</H1>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
	<H1>Bienvenido
	<sec:authentication property="name" />
	
	</H1>
	Busca arriba todas las funcionalidades pertenecientes a tu rol!
	</sec:authorize>

    <div class="row">
    
        <img src="/resources/images/Foorder1x191.png" alt="FoorderLogo"/>
       
    	
    </div>
</petclinic:layout>
