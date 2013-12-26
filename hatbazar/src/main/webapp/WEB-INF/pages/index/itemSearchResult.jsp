<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include page="leftInfo.jsp" flush="true"/>
        <div class="span8">
            <c:choose>
                <c:when test="${searchList==null || searchList.size() == 0}">
                    <div class="error-box"> 0 - Result found</div>
                </c:when>
                <c:otherwise>
                    <div class="message-box"> ${searchList.size()} - Result found</div>
                </c:otherwise>
            </c:choose>
            <c:set var="count" value="0" />
            <c:forEach items="${searchList}" var="item">
                <c:set var="count" value="${count+1}" />
                <c:if test="${count==1}">
                    <div class="row-fluid">
                </c:if>
                <div class="span4">
                    <div class="item-box">
                        <div class="popover-title">${item.name}</div>
                        <p class="text-info">${item.details}</p>
                        <p><a href="#" class="btn">Rs. ${item.price}</a></p>
                        <table>
                            <tr><th class="text-left">Contact Details</th></tr>
                            <tr><td>${item.contactPerson} : ${item.contactPhone}</td></tr>
                        </table>
                    </div>
                </div>
                <c:if test="${count%3==0}">
                    <br /><br />
                    </div><div class="row-fluid">
                </c:if>
                <c:if test="${searchList.size()==count}">
                    </div>
                </c:if>
            </c:forEach>
            <div  class="row-fluid"><br /><br /><br /></div>
        </div>
        </div><!--/span-->
    </div><!--/row-->
    <br />
</div>