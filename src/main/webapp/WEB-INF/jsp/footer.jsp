<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<div id="footer">
    <%GregorianCalendar gc = new GregorianCalendar();%>
    <p>Series Project 2011-<%=gc.get(Calendar.YEAR) %></p>
</div>