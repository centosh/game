<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">Latest Log</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" onclick="printUserLog()" title="Print users log"></a>&nbsp;&nbsp;
            </span>
        </div>
        <span id="userLog">
        <table class="table table-striped">

            <tr>
                <th>Username</th>
                <th>Name</th>
                <th>User Id</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${logList}" var="log">
                <tr>
                    <td>${log.userName}</td>
                    <td>${log.name}</td>
                    <td>${log.userId}</td>
                    <td>${log.action}</td>
                </tr>
            </c:forEach>
        </table>
        </span>
    </div>
</div>
<script type="text/javascript">
    function printUserLog(){
        $("#userLog").printElement({printMode: 'popup'});
    }
</script>