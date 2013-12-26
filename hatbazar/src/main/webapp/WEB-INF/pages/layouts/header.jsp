<%--
  Created by IntelliJ IDEA.
  User: bsejawal
  Date: 6/24/13
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="brand">Haatbazar</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="/about">About</a></li>
                    <li><a href="/contact">Contact</a></li>
                    <c:if test="${sessionScope.userType=='SUPER'}">
                        <li><a href="/user/message">Message</a></li>
                    </c:if>
                    <c:choose>
                        <c:when test="${isLogin}">
                            <li><a href="/user">Your Zone</a></li>
                            <li><a href="/item">Hatbazar Manage</a></li>
                            <li><a href="/login/logout">Logout</a></li>
                        </c:when>
                        <c:otherwise><li><a href="/login">Login</a></li></c:otherwise>
                    </c:choose>
                </ul>
                <form class="navbar-form pull-right" action="/searchItem" method="post">
                    <input type="search" name="searchQuery" id="itemSearch"  placeholder="Search" data-provide="typeahead" class="span2">
                    <button class="btn" type="submit">OK</button>
                </form>
            </div>
        </div>
    </div>
</div><br /><br /><br /><br /><br /><br />
<script type="text/javascript">
    $(document).ready(function(){
        $("#itemSearch").typeahead({
            source: function(query, process){
                return $.get('/typeahead', { query: query }, function (data) {
                    var ajaxData = data.split(",");
                    return process(ajaxData);
                });
            }
        });
    });
</script>