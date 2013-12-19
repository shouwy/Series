<%-- 
    Document   : index
    Created on : 29 août 2013, 13:40:00
    Author     : Inspiron
--%>

<%@page import="com.shouwy.series.web.util.Util"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../head.jsp" %>
        <title>Administration</title>
    </head>
    <body>
        <div id="conteneur">
            <%@include file="../header.jsp"%>
            <div id="content">
                <%@include file="left.jsp" %>
                <div id="bloc">
                <table id="table_stat">
                    <thead>
                    <tr>
                        <th>Type</th>
                        <th>Nombre Serie</th>
                        <th>Nombre Saison</th>
                        <th>Nombre Episode</th>
                        <th>Saison Par Serie</th>            
                        <th>Episode Par Saison</th>
                        <th>Episode Par Serie</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% HashMap<Type, HashMap<String, Integer>> mapStat = (HashMap<Type, HashMap<String, Integer>>) request.getAttribute("mapStat");%>
                    <% for (Type t : mapStat.keySet()){ %>
                    <tr>
                        <td><%=t.getNom() %></td>
                        <td><%=mapStat.get(t).get("series") %></td>
                        <td><%=mapStat.get(t).get("saisons") %></td>
                        <td><%=mapStat.get(t).get("episodes") %></td>
                        <td><%=Util.safeDiv(mapStat.get(t).get("saisons"), mapStat.get(t).get("series")) %></td>
                        <td><%=Util.safeDiv(mapStat.get(t).get("episodes"), mapStat.get(t).get("saisons")) %></td>
                        <td><%=Util.safeDiv(mapStat.get(t).get("episodes"), mapStat.get(t).get("series")) %></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
                
                <table style="width:100%; border: none">
                    <tr>
                        <td id="jqplot-stat"><div id="pie1"></div></td>
                        <td id="jqplot-stat"><div id="pie2"></div></td>
                    </tr>
                    <tr>
                        <td id="jqplot-stat"><div id="pie3"></div></td>
                    </tr>
                </table>
                </div>
            </div>
            <%@include file="../footer.jsp"%>
        </div>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#table_stat").dataTable({
        bFilter : false,
        bPaginate : false,
        bInfo : false,
        bJQueryUI : true,
    });

    <% String s1 = "";%>
    <% String s2 = "";%>
    <% String s3 = "";%>
    <% for (Type t : mapStat.keySet()){%>
        <% s1 += "['"+t.getNom()+"',"+mapStat.get(t).get("series")+"],"; %>
        <% s2 += "['"+t.getNom()+"',"+mapStat.get(t).get("saisons")+"],"; %>
        <% s3 += "['"+t.getNom()+"',"+mapStat.get(t).get("episodes")+"],"; %>
    <%}%>
    <% s1 = s1.substring(0, s1.lastIndexOf(",")); %>
    <% s2 = s2.substring(0, s2.lastIndexOf(",")); %>
    <% s3 = s3.substring(0, s3.lastIndexOf(",")); %>
    var s1 = [<%=s1 %>];
        
    var plot1 = $.jqplot('pie1', [s1], {
        title:"Nombre de serie",
        grid: {
            drawBorder: false, 
            drawGridlines: false,
            background: '#ffffff',
            shadow:false
        },
        axesDefaults: {
            
        },
        seriesDefaults:{
            renderer:$.jqplot.PieRenderer,
            rendererOptions: {
                showDataLabels: true
            }
        },
        legend: {
            show: true,
            rendererOptions: {
                numberRows: 1
            },
            location: 's'
        }
    });

    var s2 = [<%=s2 %>];
    var plot2 = $.jqplot('pie2', [s2], {
        title:"Nombre de saison",
        grid: {
            drawBorder: false, 
            drawGridlines: false,
            background: '#ffffff',
            shadow:false
        },
        axesDefaults: {
            
        },
        seriesDefaults:{
            renderer:$.jqplot.PieRenderer,
            rendererOptions: {
                showDataLabels: true
            }
        },
        legend: {
            show: true,
            rendererOptions: {
                numberRows: 1
            },
            location: 's'
        }
    });
    
    var s3 = [<%=s3 %>];
    var plot3 = $.jqplot('pie3', [s3], {
        title:"Nombre d'épisode",
        grid: {
            drawBorder: false, 
            drawGridlines: false,
            background: '#ffffff',
            shadow:false
        },
        axesDefaults: {
            
        },
        seriesDefaults:{
            renderer:$.jqplot.PieRenderer,
            rendererOptions: {
                showDataLabels: true
            }
        },
        legend: {
            show: true,
            rendererOptions: {
                numberRows: 1
            },
            location: 's'
        }
    }); 
});
</script>
    </body>
</html>