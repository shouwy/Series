<%-- 
    Document   : left
    Created on : 6 sept. 2013, 16:25:00
    Author     : Inspiron
--%>
<script type="text/javascript">
    $(function(){
        jQuery("#accordion-left").accordion({
            header: 'h3'
        });
        $("#accordion-left h3 a").click(function() {
            window.location = $(this).attr('href');
            return false;
        });
    });
</script>
<div id="bloc_left">
    <div id="bloc">
        <div id="accordion-left">
            <h3>Gestion</h3>
            <div>
                <a href="<%=request.getContextPath() %>/admin/list">serie</a>
                
            </div>
            <h3><a href="<%=request.getContextPath() %>/admin/admin">Statistique</a></h3>
        </div>
    </div>
</div>