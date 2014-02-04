<%-- 
    Document   : left
    Created on : 6 sept. 2013, 16:25:00
    Author     : Inspiron
--%>
<script type="text/javascript">
    $(function(){
        jQuery("#accordion-left").menu();
    });
</script>
<style>
.ui-menu .ui-menu-item{
	position : relative;
	z-index : 1000;
}
</style>
<div id="bloc_left">
        <ul id="accordion-left">
            <li><a href="#">Gestion</a>
            <ul>
                <li><a href="<%=request.getContextPath() %>/admin/series/list">Serie</a></li>
                <li><a href="<%=request.getContextPath() %>/admin/saisons/list">Saison</a></li>
                <li><a href="<%=request.getContextPath() %>/admin/episodes/list">Episode</a></li>
                <li><a href="<%=request.getContextPath() %>/admin/params/list">Type, Etat</a></li>
            </ul>    
            <li><a href="<%=request.getContextPath() %>/admin/admin">Statistique</a></li>
        </ul>
</div>