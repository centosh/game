<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include page="leftInfo.jsp" flush="true"/>
        <div class="span9">
            <div class="hero-unit">
                <h1>Contact</h1>
                <p>
                <form class="well form-horizontal" action="/contact/save" method="post" onsubmit="return validContactForm()">
                    <label><input type="text" id="contactName" name="name" class="span3" placeholder="Enter your Name" maxlength="60" required  /></label>
                    <label><input type="email" class="span3" name="email" id="contactEmail" placeholder="E-mail address" maxlength="60" required /></label>
                    <label><input type="number" class="span3" name="phone" id="contactPhone" placeholder="Phone Number"  maxlength="15" required /></label>
                    <label> <input type="text" class="span3" name="subject" id="contactSubject" placeholder="Message Subject" maxlength="100" /></label>
                    <label><textarea name="message" id="contactMessage" placeholder="Enter your Message" rows="4"></textarea></label>
                    <button type="submit" class="btn">Submit</button>
                </form>
                </p>
            </div>
        </div><!--/span-->
    </div><!--/row-->
</div>
<script type="text/javascript">
    function validContactForm(){
        var name = $("#contactName").val();
        var email = $("#contactEmail").val();
        var phone = $("#contactPhone").val();
        var subject = $("#contactSubject").val();
        var message = $("#contactMessage").val();
        if(checkNull(name)){
            alert("Name is required field");
            return false;
        }
        if(checkNull(email)){
            alert("Email is required field");
            return false;
        }else{
            if(!isEmail(email)){
                alert("Enter valid email");
                return false;
            }
        }
        if(checkNull(phone)){
            alert("Phone is required field");
            return false;
        }else{
            if(isNaN(phone)){
                alert("please enter valid Phone number");
                return false;
            }
        }
        return true

    }
</script>

