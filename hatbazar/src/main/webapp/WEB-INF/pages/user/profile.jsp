<div class="span9">
    <div class="hero-unit">
        <div class="row">
            <h4 class="pull-left">Profile</h4>
            <span class="pull-right">
                <a href="javascript:void(0)" class="icon-print" title="Print your profile" onclick="printProfile()"></a>  &nbsp;&nbsp;&nbsp;
                <a href="#profileModal" role="button" data-toggle="modal" class="icon-edit" onclick="resetForm('profileForm')" title="Edit Your Profile"></a>
            </span>
        </div>
        <div id="profileData">
            <table class="table table-striped">
                <tr><td>Name</td><td>:</td><td>${user.name}</td></tr>
                <tr><td>Address</td><td>:</td><td>${user.address}</td></tr>
                <tr><td>Email</td><td>:</td><td>${user.email}</td></tr>
                <tr><td>Phone</td><td>:</td><td>${user.phone}</td></tr>
                <tr><td>User Type</td><td>:</td><td>${user.type}</td></tr>
                <tr><td>Username</td><td>:</td><td>${user.username}</td></tr>
            </table>
        </div>
    </div>
</div>


<div id="profileModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <form method="post" action="/user/save" id="profileForm" onsubmit="return profileValid()">
        <input type="hidden" name="id" value="${user.id}" />
        <div class="modal-header">
            <a href="javascript:void(0)" class="icon-remove pull-right" data-dismiss="modal" aria-hidden="true"></a>
            <h3 id="myModalLabel">Edit Your Profile</h3>
            <div class="required-info">* Fields are required</div>
        </div>
        <div class="modal-body">
            <p>
            <div class="span9">
                <div class="hero-unit">
                    <table class="table table-striped">
                        <tr>
                            <td><label for="profileName">Name<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="text" name="name" id="profileName" value="${user.name}" required maxlength="95"/> </td>
                        </tr>
                        <tr>
                            <td><label for="profileAddress">Address</label></td>
                            <td>:</td>
                            <td><input type="text" name="address" id="profileAddress" value="${user.address}" maxlength="95"  /> </td>
                        </tr>
                        <tr>
                            <td><label for="profileEmail">Email<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="email" name="email" id="profileEmail" value="${user.email}" required /></td>
                        </tr>
                        <tr>
                            <td><label for="profilePhone">Phone<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="number" name="phone" id="profilePhone" value="${user.phone}" maxlength="15" required="" /></td>
                        </tr>
                        <tr id="tr-username">
                            <td><label for="profileUsername">Username<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="username" name="username" id="profileUsername" value="${user.username}" maxlength="20" /></td>
                        </tr>
                        <tr>
                            <td><label for="profilePassword">Change Password</label></td>
                            <td>:</td>
                            <td><input type="password" name="password" id="profilePassword" maxlength="20" /></td>
                        </tr>
                        <tr>
                            <td><label for="profileRpassword">Re-Enter Password</label></td>
                            <td>:</td>
                            <td><input type="password" name="rpassword" id="profileRpassword" maxlength="20" /></td>
                        </tr>
                    </table>
                </div>
            </div>
            </p>
        </div>
        <div class="modal-footer">
            <input type="reset" class="btn btn-inverse" data-dismiss="modal" aria-hidden="true" value="Close" />
            <input type="submit" class="btn btn-inverse" value="Save changes" />
        </div>
    </form>
</div>
<div class="hide">
    <div id="profileContainer">

    </div>
</div>
<script type="text/javascript">
    function profileValid(){
        var msg = "";
        checkNull($("#profileName").val()) ? msg= "Name is required field\n":"";
        if(checkNull($("#profileEmail").val())){
            msg ="Email is required Field\n";
        }else if(va)
            var ph = $("profilePhone").val();
        if(checkNull(ph)) msg = "Phone is required Field\n";
        else if(!isNaN(ph)) msg = "Enter Valid Phone Number\n";
        checkNull($("#profileUsername").val()) ? msg = "Username is required Field\n":"";
        if(!checkNull($("#profilePassword").val())){
            if(checkNull($("profileRpassword").val())) msg= "Re-type password is required field\n";
            else if($("#profilePassword").val()!= $("profileRpassword").val()) msg = "Re-type password not match\n"
        }
        if(checkNull(msg))return true;
        else{
            alert(msg);
            return false;
        }
    }
    function printProfile(){
        $("#profileContainer").html("");
        $("#profileContainer").append("<h3>Profile</h3>");
        $("#profileContainer").append($("#profileData").html());
        $("#profileContainer").printElement({printMode: 'popup'});
    }
</script>
