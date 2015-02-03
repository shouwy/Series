<%-- 
    Document   : list
    Created on : 5 sept. 2013, 16:35:38
    Author     : Inspiron
--%>

<%@page import="com.shouwy.series.bdd.model.Type"%>
<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page import="com.shouwy.series.bdd.model.Etat" %>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel" %>
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
    jQuery( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 'auto',
      modal: true
    });
    
    jQuery("#list").dataTable({
        bJQueryUI : true,
        sPaginationType : "full_numbers",
    });
    
     jQuery( "#create" ).button().click(function() {
        jQuery( "#dialog-form" ).dialog( "open" );
      });
  });
</script>
        <div id="conteneur">
            <%@include file="../../header.jsp"%>
            <div id="content">
                <%@include file="../left.jsp" %>
                <div id="bloc">
                <% ArrayList<Series> listSeries = (ArrayList<Series>) request.getAttribute("listSeries");%>
                <% ArrayList<Type> type = (ArrayList<Type>) request.getAttribute("type"); %>
                <% ArrayList<Etat> etat = (ArrayList<Etat>) request.getAttribute("etat"); %>
                <% ArrayList<EtatPersonnel> etatPerso = (ArrayList<EtatPersonnel>) request.getAttribute("etatPerso"); %>

                <div id="dialog-form" title="Création d'une Serie">
                    <form method="POST" id="form-series" action="<%=request.getContextPath() %>/admin/series/create">
                        <table>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Nom</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="text" id="nom" name="nom"/></td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Type</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;">
                                    <select id="type" name="type">
                                <% for(Type t : type){ %>
                                        <option value="<%=t.getId() %>"><%=t.getNom() %></option>
                                <% } %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Etat</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;">
                                    <select id="etat" name="etat">
                                <% for(Etat t : etat){ %>
                                        <option value="<%=t.getId() %>"><%=t.getNom() %></option>
                                <% } %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Etat Personnel</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;">
                                    <select id="etatperso" name="etatPerso">
                                <% for(EtatPersonnel t : etatPerso){ %>
                                        <option value="<%=t.getId() %>"><%=t.getNom() %></option>
                                <% } %>
                                    </select>
                                </td>
                            </tr>
                            <tr><td style="vertical-align:top;text-align: left;">Image</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="file" name="image"/></td>
                            </tr>
                            <tr><td style="vertical-align:top;text-align: left;">Synopsis</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><textarea id="synopsis" rows="5" cols="100" name="synopsis">Résumé de la Série</textarea></td>
                            </tr>
                            <tr><td></td><td></td><td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Créer"/></td></tr>
                        </table>
                    </form>
                </div>
                <button id="create">Créer</button>
                <table id="list">
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Type</th>
                            <th>Etat</th>
                            <th>Etat Perso</th>
                            <th>Modifier</th>
                            <th>Supprimer</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% for (Series s : listSeries){%>
                        <tr> 
                            <%
                            String typeS = "";
                            String etatS = "";
                            String etatPersoS = "";
                            for (Type t : type){
                                if (t.getId().equals(s.getIdType())){
                                    typeS = t.getNom();
                                }
                            }
                            for (Etat e : etat){
                                if (e.getId().equals(s.getIdEtat())){
                                    etatS = e.getNom();
                                }
                            }
                            for (EtatPersonnel ep : etatPerso){
                                if (ep.getId().equals(s.getIdEtatPersonnel())){
                                    etatPersoS = ep.getNom();
                                }
                            }
                            %>
                            <td><%=s.getNom() %></td>
                            <td><%=typeS %></td>
                            <td><%=etatS %></td>
                            <td><%=etatPersoS %></td>
                            <td id="centerimg"><a href="<%=request.getContextPath() %>/admin/series/modif/<%=s.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                            <td id="centerimg"><a onclick="if (confirm('Etes-vous sur de vouloir supprimer cette serie?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/series/delete/<%=s.getId()%>" class="ui-icon ui-icon-trash"></a></td>
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