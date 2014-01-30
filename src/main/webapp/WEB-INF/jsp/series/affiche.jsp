<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="com.shouwy.series.bdd.model.Etat"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../head.jsp" %>
        <title>Home Page</title>
    </head>
    <body>
        <div id="conteneur">
        <%@include file="../header.jsp"%>
        <div id="content">
            <div id="bloc">
                <div class="gauche">
                <% Series s = (Series) request.getAttribute("serie");%>
                <% HashMap<Integer, Etat> listEtat = (HashMap<Integer, Etat>) request.getAttribute("mapEtat"); %>
                <% HashMap<Integer, EtatPersonnel> listEtatPerso = (HashMap<Integer, EtatPersonnel>) request.getAttribute("mapEtatPerso"); %>
                <% HashMap<Integer, Type> type = (HashMap<Integer, Type>) request.getAttribute("mapType");%>
                <a class="titre" href="<%=request.getContextPath()%>/series/affiche/<%=s.getId()%>"><%=s.getNom()%></a>
                <p><span style="text-decoration: underline">Type</span> : <%=type.get(s.getIdType()).getNom()%></p>
                <p><span style="text-decoration: underline">Etat</span> : <%=listEtat.get(s.getIdEtat()).getNom()%></p>
                <p><span style="text-decoration: underline">Etat Personnel</span> : <%=listEtatPerso.get(s.getIdEtatPersonnel()).getNom() %></p>   
                <p><span style="text-decoration: underline">Synopsis</span> : <%=s.getSynopsis() %></p>
                </div>
                <img class="droite" alt="<%=s.getNom()%>" src="<%=request.getContextPath()%>/ressources/images/<%=s.getIdType()%>/<%=s.getImage()%>.png"  />
            </div>
        </div>
        <%@include file="../footer.jsp"%>
        </div>
    </body>
</html>