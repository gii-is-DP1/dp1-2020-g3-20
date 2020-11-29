<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
    <h2>Project ${title}</h2>
    <p><h2>Group ${group} </h2></p>
        <img src="/resources/images/Foorder1x191.png" alt="FoorderLogo"/>
        <form method="get" action="/propietarios">
      		<button class="btn btn-default" type="submit">Propietarios</button>
 		</form>
  		<form method="get" action="/managers">
      		<button class="btn btn-default" type="submit">Managers</button>
  		</form>
  		<form method="get" action="/camareros">
      		<button class="btn btn-default" type="submit">Camareros</button>
  		</form>
  		<form method="get" action="/cocinero">
      		<button class="btn btn-default" type="submit">Cocineros</button>
  		</form>
  		<form method="get" action="/proveedor">
      		<button class="btn btn-default" type="submit">Proveedores</button>
  		</form>
  		<form method="get" action="/platos">
      		<button class="btn btn-default" type="submit">Platos</button>
  		</form>
  		<form method="get" action="/producto">
      		<button class="btn btn-default" type="submit">Productos</button>
  		</form>
    	<p><ul>
    	<c:forEach items="${people}" var="person">
    		<li>${person.firstName} ${person.lastName}</li>
    	</c:forEach>
    	</ul></p>
    </div>
</petclinic:layout>
