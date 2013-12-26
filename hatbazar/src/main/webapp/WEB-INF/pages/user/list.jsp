<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">List</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" onclick="printUserList()" title="Print user list"></a>&nbsp;&nbsp;
                <a href="#userModal" role="button" data-toggle="modal" class="btn btn-inverse" onclick="prepareUserForm('new','0',this)" title="Add New User">New</a>
            </span>
        </div>
        <span id="userList">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Username</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.address}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.username}</td>
                    <td><a href="#userModal" role="button" class="icon-edit" data-toggle="modal" title="Edit" onclick="prepareUserForm('edit','${user.id}',this)"></a>&nbsp;&nbsp;<a href="/user/delete?id=${user.id}" onclick="return confirm('Are you sure to delete?')" class="icon-remove" ></a></td>
                </tr>
            </c:forEach>
        </table>
        </span>
    </div>
</div>
<div class="hide">
    <div id="userListContainer">

    </div>
</div>
<script type="text/javascript">
    function printUserList(){
        $("#userListContainer").html("");
        $("#userListContainer").append("<h3>User List</h3>");
        $("#userListContainer").append($("#userList").html());
        $("#userListContainer").find("table:first").find("tr").each(function(){
            $(this).find("th:last").remove();
            $(this).find("td:last").remove();
        })
        $("#userListContainer").printElement({printMode: 'popup'});
    }
</script>