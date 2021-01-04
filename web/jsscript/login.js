var userid,password;

function connectUser()
{
    userid=$("#username").val();
    password=$("#password").val();
    if(validate()===false)
    {
        swal("Access Denied","Please Enter Userid And Password","error");
        //    alert("Please Enter Userid And Password");
        return;
    }
    var data={
        userid:userid,
        password:password
    };
    $.post("LoginControllerServlet",data,processresponse);
}

function processresponse(responseText){
    
    responseText=responseText.trim();
    if(responseText==='error')
    {
        swal("Access Denied","Please enter valid userid/password","error");
//        alert("Please enter valid userid/password");
    }
    else if(responseText.indexOf("jsessionid")!==-1)
    {
          alert("Login Accepted");
        swal("Sucess!","Login Accepted","success");
 
        setTimeout(function()
        {
            window.location=responseText;
        },2000);
    }
    else{
//        swal("Access Denied","Some problem occured please try later","error");
        alert("Some problem occured please try later");

    }
}
function validate(){
    if(userid===""||password==="")
        return false;
    return true;
}
