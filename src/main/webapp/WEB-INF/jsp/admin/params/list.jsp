<%-- 
    Document   : list
    Created on : 3 févr. 2014, 12:38:14
    Author     : Inspiron
--%>


<%@page import="com.shouwy.series.bdd.model.Etat"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.shouwy.series.bdd.model.Saison"%>
<%@page import="com.shouwy.series.bdd.model.Series"%>
<%@page import="com.shouwy.series.bdd.model.EtatPersonnel"%>
<%@page import="com.shouwy.series.bdd.model.Type" %>
<%@page import="com.shouwy.series.bdd.model.Episode"%>
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

function drawCallBack(oTable){
    jQuery('td.edit', oTable.fnGetNodes()).editable('<%=request.getContextPath() %>/admin/params/validate', {
        "callback" : function(sValue, y){
            var aPos = oTable.fnGetPosition( this );
            oTable.fnUpdate( sValue, aPos[0], aPos[1] );
            oTable.fnDraw();
        },
        "height": "14px",
        "tooltip": "Edit…",
        "onblur": "submit"
     })
};
jQuery(document).ready(function() { 
    var typeTable = jQuery("#listType").dataTable({
        "bJQueryUI" : true,
        "sPaginationType" : "full_numbers",
    });
    drawCallBack(typeTable);

    var etatTable = jQuery("#listEtat").dataTable({
        "bJQueryUI" : true,
        "sPaginationType" : "full_numbers",
    });
    drawCallBack(etatTable);
    
    var etatPersoTable = jQuery("#listEtatPerso").dataTable({
        "bJQueryUI" : true,
        "sPaginationType" : "full_numbers",
    });
    drawCallBack(etatPersoTable);
    
    jQuery( "#type-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 'auto',
      modal: true
    });

     jQuery( "#addType" ).button().click(function() {
        jQuery( "#type-form" ).dialog( "open" );
      });

      jQuery( "#etat-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 'auto',
      modal: true
    });

     jQuery( "#addEtat" ).button().click(function() {
        jQuery( "#etat-form" ).dialog( "open" );
      });

      jQuery( "#etatPerso-form" ).dialog({
      autoOpen: false,
      height: 'auto',
      width: 'auto',
      modal: true
    });

     jQuery( "#addEtatPerso" ).button().click(function() {
        jQuery( "#etatPerso-form" ).dialog( "open" );
      });
});
</script>
        <div id="conteneur">
            <%@include file="../../header.jsp"%>
            <div id="content">
                <%@include file="../left.jsp" %>
                <div id="bloc">
                <% ArrayList<EtatPersonnel> listEtatPerso = (ArrayList<EtatPersonnel>) request.getAttribute("etatPerso"); %>
                <% ArrayList<Etat> listEtat = (ArrayList<Etat>) request.getAttribute("etat"); %>
                <button id="addType">Ajouter Un Type</button>
                <div id="type-form" title="Création d'un Type">
                    <form method="POST" action="<%=request.getContextPath() %>/admin/params/add">
                        <input type="hidden" name="type" value="type" />
                        <table>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Nom</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="text" id="nom" name="nom"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Créer"/></td>
                            </tr>
                        </table>    
                    </form>
                </div>
                    <table id="listType">
                        <caption>Type</caption>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nom</th>
                                <th>Modifier</th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Type type : listType){ %>
                            <%  Integer id = type.getId(); %>
                            <tr>
                                <td><%=type.getId()%></td>
                                <td id="type_<%=id%>" class="edit"><%=type.getNom() %></td>
                                <td id="centerimg"><a href="<%=request.getContextPath() %>/admin/params/modif/type/<%=type.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                                <td id="centerimg"><a onclick="if (confirm('Etes-vous sur de vouloir supprimer ce Type?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/params/delete/type/<%=type.getId()%>" class="ui-icon ui-icon-trash"></a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <button id="addEtatPerso">Ajouter Un Etat Personnel</button>
                    <div id="etatPerso-form" title="Création d'un Etat Personnel">
                    <form method="POST" action="<%=request.getContextPath() %>/admin/params/add">
                        <input type="hidden" name="type" value="etatPerso" />
                        <table>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Nom</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="text" id="nom" name="nom"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Créer"/></td>
                            </tr>
                        </table>    
                    </form>
                </div>
                    <table id="listEtatPerso">
                        <caption>Etat Personnel</caption>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nom</th>
                                <th>Modifier</th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (EtatPersonnel etatPerso : listEtatPerso){ %>
                            <tr>
                                <td><%=etatPerso.getId() %></td>
                                <td class="edit" id="etatPerso_<%=etatPerso.getId()%>"><%=etatPerso.getNom() %></td>
                                <td id="centerimg"><a href="<%=request.getContextPath() %>/admin/params/modif/etatPerso/<%=etatPerso.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                                <td id="centerimg"><a onclick="if (confirm('Etes-vous sur de vouloir supprimer cet Etat?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/params/delete/etatPerso/<%=etatPerso.getId()%>" class="ui-icon ui-icon-trash"></a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <button id="addEtat">Ajouter Un Etat</button>
                    <div id="etat-form" title="Création d'un Etat">
                    <form method="POST" action="<%=request.getContextPath() %>/admin/params/add">
                        <input type="hidden" name="type" value="etat" />
                        <table>
                            <tr>
                                <td style="vertical-align:top;text-align: left;">Nom</td>
                                <td style="vertical-align:top;text-align: left;"> : </td>
                                <td style="vertical-align:top;text-align: left;"><input type="text" id="nom" name="nom"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td align="right"><input class="ui-button ui-state-default ui-corner-all ui-widget" type="submit" value="Créer"/></td>
                            </tr>
                        </table>    
                    </form>
                </div>
                    <table id="listEtat">
                        <caption>Etat</caption>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nom</th>
                                <th>Modifier</th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Etat etat : listEtat){ %>
                            <tr>
                                <td><%=etat.getId() %></td>
                                <td class="edit" id="etat_<%=etat.getId()%>"><%=etat.getNom() %></td>
                                <td id="centerimg"><a href="<%=request.getContextPath() %>/admin/params/modif/etat/<%=etat.getId()%>" class="ui-icon ui-icon-tag"></a></td>
                                <td id="centerimg"><a onclick="if (confirm('Etes-vous sur de vouloir supprimer cet Etat?')){return true;}else{return false;}" href="<%=request.getContextPath() %>/admin/params/delete/etat/<%=etat.getId()%>" class="ui-icon ui-icon-trash"></a></td>
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
