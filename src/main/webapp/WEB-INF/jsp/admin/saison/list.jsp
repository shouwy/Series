<%-- 
    Document   : list
    Created on : 30 janv. 2014, 11:10:54
    Author     : Inspiron
--%>

<%@page import="java.util.HashMap"%>
<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="com.shouwy.series.bdd.model.Type" %>
<%@page import="com.shouwy.series.bdd.model.Saison"%>
<%@page import="java.util.ArrayList"%>

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
<script type="text/javascript" >
$(function() { 
    jQuery("#list").dataTable({
        bJQueryUI : true,
        sPaginationType : "full_numbers",
    });
});
</script>
        <div id="conteneur">
            <%@include file="../../header.jsp"%>
            <div id="content">
                <%@include file="../left.jsp" %>
                <div id="bloc">
                <% HashMap<Saison, Series> listSaison = (HashMap<Saison, Series>) request.getAttribute("listSaison") ;%>
                    <table id="list">
                        <thead>
                            <tr>
                                <th>Serie</th>
                                <th>Titre</th>
                                <th>Modifier</th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Saison ep : listSaison.keySet()){%>
                            <tr>
                                <td><%=listSaison.get(ep).getNom() %></td>
                                <td><%=ep.getNom()%></td>
                                <td id="centerimg"><a href="<%=request.getContextPath() %>/admin/saisons/modif/<%=ep.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                                <td id="centerimg"><a onclick="if (confirm('Etes-vous sur de vouloir supprimer cette saison?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/saisons/delete/<%=ep.getId()%>/ep" class="ui-icon ui-icon-trash"></a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <%@include file="../../footer.jsp"%>
        </div>
    </body>
</html>