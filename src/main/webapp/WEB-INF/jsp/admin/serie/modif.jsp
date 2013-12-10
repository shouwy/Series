<%-- 
    Document   : modif
    Created on : 5 sept. 2013, 16:36:14
    Author     : Inspiron
--%>

<%@page import="com.shouwy.series.web.util.Util"%>
<%@page import="com.shouwy.series.bdd.model.Episode"%>
<%@page import="com.shouwy.series.bdd.model.Saison"%>
<%@page import="com.shouwy.series.bdd.model.Etat"%>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.shouwy.series.bdd.model.Series"%>
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
    jQuery( "#saison-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 'auto',
      modal: true
    });
    
     jQuery( "#addsaison" ).button().click(function() {
        jQuery( "#saison-form" ).dialog( "open" );
      });
      
      jQuery( "#episode-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 'auto',
      modal: true
    });
    
     jQuery( "#addepisode" ).button().click(function() {
        jQuery( "#episode-form" ).dialog( "open" );
      });
      
      jQuery( "#accordion" ).accordion({
          heightStyle: "content",
          header: 'h3'
      });
      
      $("#accordion h3 a").click(function() {
            window.location = $(this).attr('href');
            return false;
        });
      
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
                <% Series s = (Series) request.getAttribute("serie");%>
                <% HashMap<Integer, Etat> listEtat = (HashMap<Integer, Etat>) request.getAttribute("mapEtat"); %>
                <% HashMap<Integer, EtatPersonnel> listEtatPerso = (HashMap<Integer, EtatPersonnel>) request.getAttribute("mapEtatPerso"); %>
                <% HashMap<Integer, Type> type = (HashMap<Integer, Type>) request.getAttribute("mapType");%>
                <% ArrayList<Saison> listSaison = (ArrayList<Saison>) request.getAttribute("saison"); %> 
                <% HashMap<Integer, ArrayList<Episode>> mapEpisodeByIdSaison = (HashMap<Integer, ArrayList<Episode>>) request.getAttribute("episode"); %>
                
                <form method="POST" action="<%=request.getContextPath() %>/admin/modif/validate">
                    <table>
                        <tr>
                            <td align="left"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Modifier"/></td>
                            <td></td></tr>
                        <tr>
                            <td style="vertical-align:top;text-align: left;"><label for="nom">Nom : </label></td>
                            <td><input type="text" value="<%=s.getNom()%>" /></td>
                        </tr>
                        <tr>
                            <td style="vertical-align:top;text-align: left;"><label for="type">Type : </label></td>
                            <td>
                                <select id="type" name="type">
                                    <% for(Integer i : type.keySet()){ %>
                                    <%String selected = (i.equals(s.getIdType()))? "selected" : "" ;%>
                                    <option value="<%=i%>" <%=selected%>><%=type.get(i).getNom() %></option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="vertical-align:top;text-align: left;"><label for="etat">Etat : </label></td>
                            <td>
                                <select id="etat" name="etat">
                                    <% for(Integer i : listEtat.keySet()){ %>
                                    <%String selected = (i.equals(s.getIdEtat())) ? "selected" : "" ;%>
                                    <option value="<%=i%>" <%=selected%>><%=listEtat.get(i).getNom() %></option>
                                    <% } %>
                                </select>
                            </td>          
                        </tr>
                        <tr>
                            <td style="vertical-align:top;text-align: left;"><label for="etatperso">Etat Personnel :</label></td>
                            <td>
                                <select id="etatperso" name="etatperso">
                                    <% for(Integer i : listEtatPerso.keySet()){ %>
                                    <%String selected = (i.equals(s.getIdEtatPersonnel()))? "selected" : "" ;%>
                                    <option value="<%=i%>" <%=selected%>><%=listEtatPerso.get(i).getNom() %></option>
                                    <% } %>
                                </select>
                            </td>            
                        </tr>
                        <tr>
                            <td style="vertical-align:top;text-align: left;"><label for="synopsis">Synopsis :</label></td>
                            <td style="vertical-align:top;text-align: left;"><textarea rows="5" cols="100" name="synopsis"><%=s.getSynopsis() %></textarea></td>
                        </tr>                   
                    </table>
                </form>
                <button id="addsaison">Ajouter Une Saison</button>
                
                <div id="saison-form" title="Création d'une Saison">
                    <form method="POST" action="<%=request.getContextPath() %>/admin/add/saison/<%=s.getId() %>">
                        <table>
                            <tr><td style="vertical-align:top;text-align: left;"><label for="nom">Nom : </label></td><td style="vertical-align:top;text-align: left;"><input type="text" id="nom" name="nom"/></td></tr>
                            <tr><td style="vertical-align:top;text-align: left;"><label for="annee">Année de Production : </label></td><td style="vertical-align:top;text-align: left;"><input type="text" id="annee" name="annee"/></td></tr>
                            <tr><td></td><td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Créer"/></td></tr>
                        </table>    
                    </form>
                </div>
                <% if (listSaison.size() > 0) { %>
                <button id="addepisode">Ajouter Un Episode</button>
                <div id="episode-form" title="Création d'un Episode">
                    <form method="POST" action="<%=request.getContextPath() %>/admin/add/episode/<%=s.getId() %>">
                        <table>
                            <tr>
                                <td style="vertical-align:top;text-align: left;"><label for="saison">Saison : </label></td>
                                <td style="vertical-align:top;text-align: left;">
                                    <select id="saison" name="saison">
                                        <% for(Saison saison : listSaison) { %>
                                        <option value="<%=saison.getId() %>"><%=saison.getNom()%></option>
                                        <% } %>
                                    </select>
                                </td>
                            </tr>
                            <tr><td style="vertical-align:top;text-align: left;"><label for="titre">Titre : </label></td><td style="vertical-align:top;text-align: left;"><input type="text" id="titre" name="titre"/></td></tr>
                            <tr><td style="vertical-align:top;text-align: left;"><label for="date">Date de Sortie : </label></td><td style="vertical-align:top;text-align: left;"><input type="text" id="date" name="date"/></td></tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;"><label for="etatperso">Etat Personnel :</label></td>
                                <td style="vertical-align:top;text-align: left;">
                                     <select id="etatperso" name="etatperso">
                                    <% for(Integer i : listEtatPerso.keySet()){ %>
                                        <option value="<%=i%>"><%=listEtatPerso.get(i).getNom() %></option>
                                    <% } %>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="vertical-align:top;text-align: left;"><label for="synopsis">Synopsis :</label></td>
                                <td style="vertical-align:top;text-align: left;"><textarea rows="5" cols="100" name="synopsis"></textarea></td>
                            </tr>
                            <tr><td></td><td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Créer"/></td></tr>
                        </table>    
                    </form>
                </div>
                <% } %>
                <div id="bloc">
                    <div id="accordion">
                        <%for(Saison saison : listSaison) { %>
                        <h3>
                            <table>
                                <tr>
                                    <td><%=saison.getNom()%></td>
                                    <td><a href="<%=request.getContextPath() %>/admin/saison/modif/<%=saison.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                                    <td><a onclick="if (confirm('Etes-vous sur de vouloir supprimer cette Saison?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/saison/delete/<%=saison.getId()%>" class="ui-icon ui-icon-trash"></a></td>
                                </tr>
                            </table>
                        </h3>
                        <div>
                            <% ArrayList<Episode> listEpisode = mapEpisodeByIdSaison.get(saison.getId());%>
                            <% if(listEpisode.size() > 0){ %>
                            <table>
                                <tr>
                                    <th>Titre</th>
                                    <th>Date de Sortie</th>
                                    <th>Etat Personnel</th>
                                    <th>Modifier</th>
                                    <th>Supprimer</th>
                                </tr>
                                <% for (Episode e : listEpisode) { %>
                                <tr>
                                    <td><%=e.getTitre()%></td>
                                    <td><%=Util.getDateInString(e.getDateSortie()) %></td>
                                    <td><%=listEtatPerso.get(e.getIdEtatPersonnel()).getNom() %></td>
                                    <td><a href="<%=request.getContextPath() %>/admin/episode/modif/<%=e.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                                    <td><a onclick="if (confirm('Etes-vous sur de vouloir supprimer cette Episode?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/delete/<%=e.getId()%>" class="ui-icon ui-icon-trash"></a></td>
                                </tr>
                                <% } %>
                            </table>
                            <% } %>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
            </div>
            <%@include file="../../footer.jsp"%>
        </div>
    </body>
</html>