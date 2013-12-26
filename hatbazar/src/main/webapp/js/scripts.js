function prepareUserForm(flag,id,el){
    resetForm("userForm");
    if(flag=='new'){
        $("#userModalLabel").text("Add New User");
        $("#lbl_user_password").text("Password");
        userNewForm();
    }else if(flag=='edit'){
        $("#userModalLabel").text("Edit User");
        $("#lbl_user_password").text("Change Password");
        userFormRemoveRequiredAttribute();
        setUserFormData(getUserEdit(el));
        $("#userForm").append("<input type='hidden' name='id' value='"+id+"' />");
    }
}
function userNewForm(){
    userFormRemoveRequiredAttribute();
    $("#lbl_user_password").append("<span class='info-required'>*</span>");
    $("#lbl_user_rpassword").append("<span class='info-required'>*</span>");
    $("#user_rpassword").prop("required","true");
    $("#user_password").prop("required","true");
}

function userFormRemoveRequiredAttribute(){
    $("#lbl_user_password").find(".info-required").remove();
    $("#lbl_user_rpassword").find(".info-required").remove();
    $("#user_rpassword").removeProp("required");
    $("#user_password").removeProp("required");
}

function resetForm(form){
    $('#'+form).each(function(){
        this.reset();
    });
}

function getUserEdit(el){
    var e = $(el).parent().parent();
    var data=new Object();
    data.name= e.find("td:eq(0)").text();
    data.address=e.find("td:eq(1)").text();
    data.email=e.find("td:eq(2)").text();
    data.phone=e.find("td:eq(3)").text();
    data.username=e.find("td:eq(4)").text();
    return data;

}
function setUserFormData(data){
    $("#name").val(data['name']);
    $("#address").val(data['address']);
    $("#email").val(data['email']);
    $("#phone").val(data['phone']);
    $("#username").val(data['username']);
}
function adminTab(tab){
    if(!checkNull(tab)){
        $("#tab-"+tab).tab("show");
    }
}

function checkNull(st){
    return st=="" || st == null || st == "undefined";
}

function prepareItemForm(flag,id,el){
    resetForm("itemForm");
    if(flag=='new'){
        $("#myModalLabel").text("Add New Item");
    }else if(flag=='edit'){
        setItemFormData(getItemEdit(el));
        $("#userForm").append("<input type='hidden' name='id' value='"+id+"' />");
        $("#myModalLabel").text("Edit Item");
    }
}

function getItemEdit(el){
    var e = $(el).parent().parent();
    var data=new Object();
    data.name= e.find("td:eq(0)").text();
    data.category=e.find("td:eq(1)").text();
    data.price=e.find("td:eq(2)").text();
    data.status=e.find("td:eq(3)").text();
    data.contactPerson=e.find("td:eq(4)").text();
    data.contactPhone=e.find("td:eq(5)").text();
    data.details=e.find("td:eq(6)").text();
    return data;
}

function setItemFormData(data){
    $("#name").val(data['name']);
    $("#price").val(data['price']);
    $("#contactPerson").val(data['contactPerson']);
    $("#contactPhone").val(data['contactPhone']);
    $("#details").val(data['details']);
    if(data['category'].trim()=="CATTLE"){
        $("#item-category-cattle").attr("checked", "checked");
    }else if(data['category'].trim()=="VEGETABLES"){
        $("#item-category-vegetables").attr("checked", "checked");
    }else if(data['category'].trim()=="LAND"){
        $("#item-category-land").attr("checked", "checked");
    }
    if(data['status'].trim()=="ACTIVE"){
        $("#status_active").attr("checked", "checked");
    }else if(data['status'].trim()=="DEACTIVE"){
        $("#status_deactive").attr("checked", "checked");
    }
}



//global script
function isEmail(email) {
    var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}

function getMessage(id){
    $.ajax({
        url: "/user/getMessage",
        type: "GET",
        dataType:"text",
        data: {id:id},
        success:function(data){
            getMessageProperty(data);
        }
    })
}
function getMessageProperty(data){
    var ajaxData=data.split("#");
    $("#sendBy").html(ajaxData[0]);
    $("#senderEmail").html(ajaxData[1]);
    $("#senderPhone").html(ajaxData[2]);
    $("#senderSubject").html(ajaxData[3]);
    $("#senderMessage").html(ajaxData[4]);
    $("#viewMessage").modal("show");
}