<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="com.shouwy.series.bdd.model.Etat"%>
<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <% Type type = (Type) request.getAttribute("type");%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../head.jsp" %>
        <title>Liste des <%=type.getNom() %></title>
    </head>
    <body>
<script type="text/javascript" >
$(function() {
    jQuery("#list").dataTable({
        bJQueryUI : true,
        sPaginationType : "full_numbers",
        aoColumns : [
          { "sWidth" : "12%" },
          { "sWidth" : "12%" },
          { "sWidth" : "12%" },
          { "sWidth" : "60%" },
        ],
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
                    <th>Nom</th>
                    <th>Etat</th>
                    <th>Etat Personnel</th>
                    <th>Synopsis</th>
                    
                </tr>
            </thead>
            <tbody>
                <% HashMap<Integer, Etat> etat = (HashMap<Integer, Etat>) request.getAttribute("mapEtat"); %>
                <% HashMap<Integer, EtatPersonnel> etatPerso = (HashMap<Integer, EtatPersonnel>) request.getAttribute("mapEtatPerso"); %>
                <% ArrayList<Series> listSeries = (ArrayList<Series>) request.getAttribute("list"); %>
                <% for (Series s : listSeries){ %>    
                <tr class="overTab" onclick="document.location='<%=request.getContextPath() %>/series/affiche/<%=s.getId() %>'">
                    <%
                        String etatS = etat.get(s.getIdEtat()).getNom();
                        String etatPersoS = etatPerso.get(s.getIdEtatPersonnel()).getNom();
                        
                    %>
                    <td><%=s.getNom() %></td>
                    <td><%=etatS %></td>
                    <td><%=etatPersoS%></td>
                    <td style="text-align: justify"><%=s.getSynopsis()%></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        </div>
        <%@include file="../footer.jsp"%>
        </div>
    </body>
</html>
