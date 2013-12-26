<script type="text/javascript">
    $(document).ready(function(){
        $("#username").focus();
    });

</script>
<meta name="decorator" content="login"/>
<div class="container"><br/><br/><br/><br/><br/><br/>
    <form class="form-signin" action="/login/authenticate" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" id="username" name="username" class="input-block-level" placeholder="Username" autocomplete="off" required />
        <input type="password" name="password" class="input-block-level" placeholder="Password" required />
        <input class="btn btn-inverse" type="submit" value="Sign in" />
    </form>

</div>
