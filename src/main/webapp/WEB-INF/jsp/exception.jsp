<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="resources/images/cocina_en_llamas.jpg" var="errorImage"/>
    <p class="text-center"><img src="${errorImage}" alt="Error_img"/></p>

    <h2 align="center">Vaya! Parece que ha ocurrido un error :C vuelva a Home</h2>

    <p>${exception.message}</p>

</petclinic:layout>
