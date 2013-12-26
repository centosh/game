<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include page="leftInfo.jsp" flush="true"/>
        <!--/span-->
        <div class="span9">
            <div class="hero-unit">
                <h2>Welcome to Haatbazar!</h2>
                <p>The <strong>Haatbazar System</strong> is   a village based project. This project is related with requesting for bye goods and selling of goods on remote areas of Nepal. It helps to find every domestic selling and purchasing goods online. Where seller can put their goods for sale and buyer can easily search ,book available goods and go to the respective place for its purchase.
                    a village computer operator  will have access to this application. The person  will  explain about the items available in the application. People from different region will  contact the operator and operator will act as  a mediator between them.
                </p>
            </div>
            <c:set var="count" value="0" />
            <c:forEach items="${list}" var="item">
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
                <c:if test="${list.size()==count}">
                    </div>
                </c:if>
            </c:forEach>
            <div  class="row-fluid"><br /><br /><br /></div>
        </div><!--/span-->
    </div><!--/row-->
    <br />
</div>