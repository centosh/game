<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span13">
    <div class="hero-unit">
        <h4 class="pull-left">Un-read Message</h4>
        <table class="table table-striped">
            <tr>
                <th>Send By</th>
                <th>Subject</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${unRead}" var="contact">
                <tr>
                    <td>${contact.name}</td>
                    <td>${contact.subject}</td>
                    <td>
                        <a href="/user/messageDelete?id=${contact.id}" onclick="return confirm('Are you sure to delete?')" class="icon-remove" title="Delete"></a>&nbsp;
                        <a href="javascript:void(0)" class="btn btn-inverse" title="View Details" onclick="getMessage('${contact.id}')">View</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>