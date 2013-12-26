<script type="text/javascript">
    $(document).ready(function(){
        adminTab("${tab}");
    });
</script>

<ul class="nav nav-tabs" id="myTab">
    <li class="active"><a id="tab-your_list" data-toggle="tab" href="#your_list">Your List</a></li>
    <li><a id="tab-available" data-toggle="tab" href="#available">Available List</a></li>
    <li><a id="tab-reserved" data-toggle="tab" href="#reserved">Reserved List</a></li>
    <li><a id="tab-sold" data-toggle="tab" href="#sold">Sold List</a></li>
</ul>
<div class="tab-content" id="myTabContent">
    <div id="your_list" class="tab-pane fade in active">
        <p><jsp:include page="_yourItems.jsp" flush="true" /></p>
    </div>
    <div id="available" class="tab-pane fade in">
        <p><jsp:include page="_available.jsp" flush="true" /></p>
    </div>
    <div id="reserved" class="tab-pane fade in">
        <p><jsp:include page="_reserved.jsp" flush="true" /></p>
    </div>
    <div id="sold" class="tab-pane fade in">
        <p><jsp:include page="_sold.jsp" flush="true" /></p>
    </div>
    <jsp:include page="form.jsp" flush="true" />
</div>