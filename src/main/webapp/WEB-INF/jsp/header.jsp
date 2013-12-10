
<%@page import="com.shouwy.series.bdd.dao.face.TypeDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shouwy.series.bdd.model.Type"%>
<h1 id="header"><a href="<%=request.getContextPath()%>/" title="Series Project"></a></h1>
<ul id="menu">
    <li><a class='fg-button ui-state-default' href="<%=request.getContextPath()%>/">Accueil</a></li>
    <%ArrayList<Type> listType = (ArrayList<Type>) request.getAttribute("listType");%>
    <% for (Type t : listType){%>
    <li><a class='fg-button ui-state-default' href="<%=request.getContextPath()%>/series/list/<%=t.getId()%>"><%=t.getNom()%></a></li>
    <%}%>
    <li id="admin" ><a class='fg-button ui-state-default' href="<%=request.getContextPath()%>/admin/admin">Admin</a></li>
</ul>