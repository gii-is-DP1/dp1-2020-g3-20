
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="empleados">
	<div class="Container-Empleados">
		<div class="Container-camareros">
		 	<spring:url value="/camareros" var="camareroURL"></spring:url>
			<a href="${fn:escapeXml(camareroURL)}"><img  src="resources/images/camarero.jpg"></a>
		 	<br>
        	<h1><a href="${fn:escapeXml(camareroURL)}">CAMARERO</a></h1>
		</div>
		
		<div class="Container-cocinero">
			<spring:url value="/cocinero" var="cocineroURL"></spring:url>
        	<a href="${fn:escapeXml(cocineroURL)}"><img  src="resources/images/cocinero.jpg"></a>
         	<br>
        	<h1><a href="${fn:escapeXml(cocineroURL)}">COCINERO</a></h1>
		</div>
		
		<div class="Container-managers">
			<spring:url value="/managers" var="managersURL"></spring:url>
			<a href="${fn:escapeXml(managersURL)}"><img  src="resources/images/manager.jpg"></a>
		 	<br>
        	<h1> <a href="${fn:escapeXml(managersURL)}">MANAGERS </a></h1>    
		</div>
	</div>		

    </petclinic:layout>
    
