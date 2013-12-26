<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">Sold Items</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" onclick="printSoldItem()"></a>
            </span>
        </div>
        <span class="soldItems">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Contact Person</th>
                <th>Contact Phone</th>
            </tr>
            <c:forEach items="${soldList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>${item.price}</td>
                    <td>${item.contactPerson}</td>
                    <td>${item.contactPhone}</td>
                </tr>
            </c:forEach>
        </table>
            </span>
    </div>
</div>
<div class="hide">
    <div id="soldItemsContailer">

    </div>
</div>
<script type="text/javascript">
    function printSoldItem(){
        $("#soldItemsContailer").html("");
        $("#soldItemsContailer").append("<h3>Sold List</h3>");
        $("#soldItemsContailer").append($("#soldItems").html());
        $("#soldItemsContailer").find("table:first").find("tr").each(function(){
            $(this).find("th:last").remove();
            $(this).find("td:last").remove();
        })
        $("#soldItemsContailer").printElement({printMode: 'popup'});
    }
</script>