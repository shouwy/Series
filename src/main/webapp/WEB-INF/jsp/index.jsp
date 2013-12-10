<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="com.shouwy.series.bdd.model.Etat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shouwy.series.bdd.model.Type"%>
<%@page import="com.shouwy.series.bdd.model.Series" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="head.jsp" %>
        <title>Home Page</title>
    </head>
    <body>
        <div id="conteneur">
        <%@include file="header.jsp"%>
        <div id="content">
            <h1 class="main">Accueil</h1>
        <% HashMap<Integer, Etat> listEtat = (HashMap<Integer, Etat>) request.getAttribute("mapEtat"); %>
        <% HashMap<Integer, EtatPersonnel> listEtatPerso = (HashMap<Integer, EtatPersonnel>) request.getAttribute("mapEtatPerso"); %>
        <% HashMap<Type, Series> mapSeries = (HashMap<Type, Series>) request.getAttribute("mapSeries"); %>
        <% HashMap<Integer, Type> type = (HashMap<Integer, Type>) request.getAttribute("mapType");%>
        <%for (Type t : mapSeries.keySet()){ %>          
            <div id="bloc">
                <a class="titre" href="<%=request.getContextPath()%>/series/affiche/<%=mapSeries.get(t).getId()%>"><%=mapSeries.get(t).getNom()%></a>
                <p><span style="text-decoration: underline">Type</span> : <%=t.getNom()%></p>
                <p><span style="text-decoration: underline">Etat</span> : <%=listEtat.get(mapSeries.get(t).getIdEtat()).getNom()%></p>
                <p><span style="text-decoration: underline">Etat Personnel</span> : <%=listEtatPerso.get(mapSeries.get(t).getIdEtatPersonnel()).getNom() %></p>
                <p><span style="text-decoration: underline">Synopsis</span> : <%=mapSeries.get(t).getSynopsis() %></p>
            </div>      
        <%}%>
        </div>
        <%@include file="footer.jsp"%>
        </div>
    </body>
</html>
