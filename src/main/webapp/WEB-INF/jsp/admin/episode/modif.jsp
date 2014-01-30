<%-- 
    Document   : modif
    Created on : 13 déc. 2013, 15:05:04
    Author     : Inspiron
--%>

<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page import="com.shouwy.series.web.util.Util"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="com.shouwy.series.bdd.model.Saison"%>
<%@page import="com.shouwy.series.bdd.model.Episode"%>
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
    jQuery("#date").datepicker({
        dateFormat:"dd/mm/yy"
    });
});
</script>
        <div id="conteneur">
            <%@include file="../../header.jsp"%>
            <div id="content">
                <%@include file="../left.jsp" %>
                <div id="bloc">
                    <% Episode e = (Episode) request.getAttribute("episode");%>
                    <% Series s = (Series) request.getAttribute("serie");%>
                    <% HashMap<Integer, EtatPersonnel> listEtatPerso = (HashMap<Integer, EtatPersonnel>) request.getAttribute("mapEtatPerso"); %>
                    <% ArrayList<Saison> listSaison = (ArrayList<Saison>) request.getAttribute("saison"); %> 
                    <% String msg = (String) request.getAttribute("msg"); %>
                    
                    <% if (msg != null){ %>
                    <p class="up"><%=msg%>
                        <button id="backseries" class="ui-button ui-state-default ui-corner-all ui-widget">
                            <a href="<%=request.getContextPath() %>/admin/series/modif/<%=s.getId() %>">Retour à La Serie</a>
                        </button>
                        </p>
                    <% } %>
                    <form method="POST" action="<%=request.getContextPath() %>/admin/episodes/valide/<%=e.getId()%>">
                        <table>
                            <tr>
                                <td align="left"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Modifier"/></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Serie</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><%=s.getNom()%></td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Saison</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;">
                                    <select id="saison" name="saison">
                                        <% for(Saison saison : listSaison) { %>
                                        <% String selected = (saison.getId().equals(e.getIdSaison()))? "selected" : "" ;%>
                                        <option value="<%=saison.getId() %>" <%=selected%> ><%=saison.getNom()%></option>
                                        <% } %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Titre</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="text" id="titre" name="titre" value="<%=e.getTitre() %>"/></td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Date de Sortie</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="text" id="date" name="date" value="<%=Util.convertCalToString(e.getDateSortie()) %>"/></td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Etat Personnel</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;">
                                     <select id="etatperso" name="etatperso">
                                    <% for(Integer i : listEtatPerso.keySet()){ %>
                                        <%String selected = (i.equals(e.getIdEtatPersonnel()))? "selected" : "" ;%>
                                        <option value="<%=i%>" <%=selected%> ><%=listEtatPerso.get(i).getNom() %></option>
                                    <% } %>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Synopsis</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><textarea rows="5" cols="100" name="synopsis"><%= e.getSynopsis() %></textarea></td>
                            </tr>
                        </table>                        
                    </form>                        
                </div>
            </div>
            <%@include file="../../footer.jsp"%>
        </div>
    </body>
</html>