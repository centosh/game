<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">Reserved Items</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" onclick="printReservedItem()"></a>
            </span>
        </div>
        <span class="reservedItems">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Contact Person</th>
                <th>Contact Phone</th>
                <th>Details</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${reservedList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>${item.price}</td>
                    <td>${item.contactPerson}</td>
                    <td>${item.contactPhone}</td>
                    <td>${item.details}</td>
                    <td>
                        <a href="/item/cancelReserved?id=${item.id}" role="button" class="btn btn-inverse"  title="Cancel This Reserved" onclick="return confirm('Are you really want to cancel this reserved?')">Cancel Reserved</a>
                        <a href="/item/sold?id=${item.id}" role="button" class="btn btn-inverse"  title="Sold" onclick="return confirm('Are you really want to move this to sold?')">Sold</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
       </span>
    </div>
</div>
<div class="hide">
    <div id="reservedItemsContailer">

    </div>
</div>
<script type="text/javascript">
    function printReservedItem(){
        $("#reservedItemsContailer").html("");
        $("#reservedItemsContailer").append("<h3>Reserved Items</h3>");
        $("#reservedItemsContailer").append($("#reservedItems").html());
        $("#reservedItemsContailer").find("table:first").find("tr").each(function(){
            $(this).find("th:last").remove();
            $(this).find("td:last").remove();
        })
        $("#reservedItemsContailer").printElement({printMode: 'popup'});
    }
</script>