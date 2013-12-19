<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../head.jsp" %>
        <title>Liste des </title>
    </head>
    <body>
<script type="text/javascript" >
$(function() {
    jQuery("#list").dataTable({
        bJQueryUI : true,
        sPaginationType : "full_numbers",
    });
});
</script>
        <div id="conteneur">
        <%@include file="../header.jsp"%>
        <div id="content">
        <h1>Tableau de Series</h1>
        <table id="list">
            <thead>
                <tr>
                    <td>Nom</td>
                    <td>Synopsis</td>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach items="${list}" var="series" >    
                <tr>
                    <td>${series.nom}</td>
                    <td>${series.synopsis}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        <%@include file="../footer.jsp"%>
        </div>
    </body>
</html>
