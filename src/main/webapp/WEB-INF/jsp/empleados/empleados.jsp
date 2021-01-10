
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

                   <spring:url value="/camareros" var="camareroURL">
                   </spring:url>
                   <h1 align="left"><a href="${fn:escapeXml(camareroURL)}"><img  src="resources/images/camarero.jpg"></a>CAMARERO</h1>
    
                       <spring:url value="/cocinero" var="cocineroURL">
                   	
                   </spring:url>
                   <h1 align="right">COCINERO<a href="${fn:escapeXml(cocineroURL)}"><img  src="resources/images/cocinero.jpg"></a> </h1>
    
                       <spring:url value="/managers" var="managersURL">
                   </spring:url>
                   <h1> <a href="${fn:escapeXml(managersURL)}"><img  src="resources/images/manager.jpg"></a> MANAGERS </h1>
    

    
    
    </petclinic:layout>
    
