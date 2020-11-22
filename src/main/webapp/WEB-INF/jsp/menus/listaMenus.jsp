<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="menus">
    <h2>Menu</h2>

    <table id="menusTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${menus}" var="menu">
            <tr>
                <td>
                    <c:out value="${menu.name}"/>
                </td>
                <td>
                   <spring:url value="/menus/delete/{menuId}" var="menuURL">
                   		  <spring:param name="menuId" value="${menu.id}"/>
                   </spring:url>
                   <a href="${fn:escapeXml(menuURL)}">Delete</a>
                </td>
      
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
