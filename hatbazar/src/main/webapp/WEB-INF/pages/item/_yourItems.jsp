<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">List</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" onclick="printYourItems()"></a>&nbsp;&nbsp;
                <a href="#itemModal" role="button" data-toggle="modal" class="btn btn-inverse" onclick="prepareItemForm('new','0',this)" title="Add New Item">Add</a>
            </span>
        </div>
        <span id="yourItems">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Status</th>
                <th>Contact Person</th>
                <th>Contact Phone</th>
                <th>Details</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${yourList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>${item.price}</td>
                    <td>${item.status}</td>
                    <td>${item.contactPerson}</td>
                    <td>${item.contactPhone}</td>
                    <td>${item.details}</td>
                    <td>
                        <a href="#itemModal" role="button" class="icon-edit" data-toggle="modal" title="Edit" onclick="prepareItemForm('edit','${item.id}',this)"></a>&nbsp;&nbsp;
                        <a href="/item/delete?id=${item.id}" onclick="return confirm('Are you sure to delete?')" class="icon-remove" title="Delete"></a>
                        <c:if test="${item.status=='ACTIVE'}">&nbsp;&nbsp;
                            <a href="/item/reserve?id=${item.id}" onclick="return confirm('Are you really want to  Reserve this?')" class="btn btn-inverse" title="Reserve">Reserve</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
            </span>
    </div>
</div>

<div class="hide">
    <div id="yourItemsContainer">

    </div>
</div>

<script type="text/javascript">
    function printYourItems(){
        $("#yourItemsContainer").append("<h3>Your Item List</h3>");
        $("#yourItemsContainer").append($("#yourItems").html());
        $("#yourItemsContainer").find("table:first").find("tr").each(function(){
            $(this).find("th:last").remove();
            $(this).find("td:last").remove();
        })
        $("#yourItemsContainer").printElement({printMode: 'popup'});
    }
</script>