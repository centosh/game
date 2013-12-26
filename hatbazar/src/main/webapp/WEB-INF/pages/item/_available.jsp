<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">Available Items</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" onclick="printAvailableItem()"></a>
            </span>
        </div>
        <span class="availableItems">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Contact Person</th>
                <th>Contact Phone</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${availableList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>${item.price}</td>
                    <td>${item.contactPerson}</td>
                    <td>${item.contactPhone}</td>
                    <td>
                        <a href="/item/reserve?id=${item.id}" role="button" class="btn btn-inverse" data-toggle="modal" title="Reserve this this" onclick="return confirm('Are you really want to reserve this?')">Reserve</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
            </span>
    </div>
</div>
<div class="hide">
    <div id="availableItemsContailer">

    </div>
</div>
<script type="text/javascript">
    function printAvailableItem(){
        $("#availableItemsContailer").html("");
        $("#availableItemsContailer").append("<h3>Available Items</h3>");
        $("#availableItemsContailer").append($("#availableItems").html());
        $("#availableItemsContailer").find("table:first").find("tr").each(function(){
            $(this).find("th:last").remove();
            $(this).find("td:last").remove();
        })
        $("#availableItemsContailer").printElement({printMode: 'popup'});
    }
</script>