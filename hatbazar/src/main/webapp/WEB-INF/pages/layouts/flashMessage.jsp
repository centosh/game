<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="error-wrapper">
    <c:if test="${not empty error}">
        <div class="error-box">
            <div class="icon-close" onclick="hideFlashMessage()" title="Hide this message bar">&nbsp;</div>
            ${error}
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message-box">
            <div class="icon-close" onclick="hideFlashMessage()" title="Hide this message bar">&nbsp;</div>
            ${message}
        </div>
    </c:if>

    <c:if test="${not empty warning}">
        <div class="warning-box">
            <div class="icon-close" onclick="hideFlashMessage()" title="Hide this message bar">&nbsp;</div>
            ${warning}
        </div>
    </c:if>
</div>

<script type="text/javascript">
    $(document).ready( function() {
        $('.error-wrapper').delay(10000).fadeOut();
    });
function hideFlashMessage(){
    $('.error-wrapper').hide();
}
</script>
