<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
    $(document).ready(function(){
        adminTab("${tab}");
    });
</script>
<ul class="nav nav-tabs" id="myTab">
    <li class="active"><a id="tab-unread" data-toggle="tab" href="#unread">Unread Message</a></li>
    <li><a id="tab-unreadt" data-toggle="tab" href="#read">ReadMessage</a></li>
</ul>
<div class="tab-content" id="myTabContent">
    <div id="unread" class="tab-pane fade in active">
        <p><jsp:include page="unread.jsp" flush="true" /></p>
    </div>
        <div id="read" class="tab-pane fade">
            <p><jsp:include page="read.jsp" flush="true" /></p>
        </div>
        <jsp:include page="view.jsp" flush="true" />
</div>