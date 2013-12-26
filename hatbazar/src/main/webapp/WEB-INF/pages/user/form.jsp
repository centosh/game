<div id="userModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <form method="post" action="/user/save" id="userForm">
        <div class="modal-header">
            <a href="javascript:void(0)" class="icon-remove pull-right" data-dismiss="modal" aria-hidden="true"></a>
            <h3 id="userModalLabel">User</h3>
            <div class="required-info">* Fields are required</div>
        </div>
        <div class="modal-body">
            <p>
            <div class="span9">
                <div class="hero-unit">
                    <table class="table table-striped">
                        <tr>
                            <td><label for="name">Name<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="text" name="name" id="name" required maxlength="95"/> </td>
                        </tr>
                        <tr>
                            <td><label for="address">Address</label></td>
                            <td>:</td>
                            <td><input type="text" name="address" id="address" maxlength="95"  /> </td>
                        </tr>
                        <tr>
                            <td><label for="email">Email<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="email" name="email" id="email" required /></td>
                        </tr>
                        <tr>
                            <td><label for="phone">Phone<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="number" name="phone" id="phone" maxlength="15" required /></td>
                        </tr>
                        <tr>
                            <td><label for="username">Username<span class="info-required">*</span></label></td>
                            <td>:</td>
                            <td><input type="username" name="username" id="username" maxlength="20" required /></td>
                        </tr>
                        <tr>
                            <td><label for="user_password" id="lbl_user_password">Password</label></td>
                            <td>:</td>
                            <td><input type="password" name="password" id="user_password" maxlength="20" /></td>
                        </tr>
                        <tr>
                            <td><label for="user_rpassword" id="lbl_user_rpassword">Re-Enter Password</label></td>
                            <td>:</td>
                            <td><input type="password" name="rpassword" id="user_rpassword" maxlength="20" /></td>
                        </tr>
                    </table>
                </div>
            </div>
            </p>
        </div>
        <div class="modal-footer">
            <input type="reset" class="btn btn-inverse" data-dismiss="modal" aria-hidden="true" value="Close" />
            <input type="submit" class="btn btn-inverse" value="Save" />
        </div>
    </form>
</div>
