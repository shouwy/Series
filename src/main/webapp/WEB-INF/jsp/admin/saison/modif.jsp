<%-- 
    Document   : modif
    Created on : 20 sept. 2013, 13:42:32
    Author     : Inspiron
--%>

<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page import="com.shouwy.series.bdd.model.Saison"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../../head.jsp" %>
        <title>Administration</title>
    </head>
    <body>
        <div id="conteneur">
            <%@include file="../../header.jsp"%>
            <div id="content">
                <%@include file="../left.jsp" %>
                <div id="bloc">
                    <% Series serie = (Series) request.getAttribute("serie"); %>
                    <h3>Saison de la Serie : <%=serie.getNom() %></h3>
                    <% Saison saison = (Saison) request.getAttribute("saison"); %>
                    <form method="POST" action="<%=request.getContextPath() %>/admin/saison/valide/<%=saison.getId() %>">
                        <table>
                            <tr><td style="vertical-align:top;text-align: left;"><label for="nom">Nom : </label></td><td style="vertical-align:top;text-align: left;"><input type="text" id="nom" name="nom" value="<%=saison.getNom() %>"/></td></tr>
                            <tr><td style="vertical-align:top;text-align: left;"><label for="annee">Année de Production : </label></td><td style="vertical-align:top;text-align: left;"><input type="text" id="annee" name="annee" value="<%=saison.getAnneeProduction() %>"/></td></tr>
                            <tr><td></td><td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Modifier"/></td></tr>
                        </table>    
                    </form>
                </div>
            </div>
            <%@include file="../../footer.jsp"%>
        </div>
    </body>
</html>