<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="resources/images/cocina_en_llamas.jpg" var="petsImage"/>
    <h1 align="center"><img src="${petsImage}"/></h1>

    <h2 align="center">Vaya! Parece que ha ocurrido un error :C, vuelva a Home</h2>

    <p>${exception.message}</p>

</petclinic:layout>
