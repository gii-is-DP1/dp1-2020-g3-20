
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
		 	<form class="empl" action="${fn:escapeXml(camareroURL)}"> 
      			<button class="btn btn-default" type="submit">CAMARERO</button>
  			</form>
		</div>
		
		<div class="Container-cocinero">
			<spring:url value="/cocinero" var="cocineroURL"></spring:url>
        	<a href="${fn:escapeXml(cocineroURL)}"><img  src="resources/images/cocinero.jpg"></a>
         	<br>
         	<form class="empl" action="${fn:escapeXml(cocineroURL)}"> 
      			<button class="btn btn-default" type="submit">COCINERO</button>
  			</form>
		</div>
		
		<div class="Container-managers">
			<spring:url value="/managers" var="managersURL"></spring:url>
			<a href="${fn:escapeXml(managersURL)}"><img  src="resources/images/manager.jpg"></a>
		 	<br>
		 	<form class="empl" action="${fn:escapeXml(managersURL)}"> 
      			<button class="btn btn-default" type="submit">MANAGERS</button>
  			</form>   
		</div>
	</div>		

    </petclinic:layout>
    
