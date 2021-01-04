var username,password,cpassword,city,address,adhar,email,mobile;
function addUser(){
    
    username=$("#username").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    if(validateUser()){
        
        if(password!==cpassword){
            swal("Error!","password does not match","error");
            return ;
        }
        else{
            if(checkEmail(email)===false)
                return ;
                var data= {
                    username:username,
                    password:password,
                    userid:adhar,
                    email:email,
                    mobile:mobile,
                    address:address,
                    city:city
                };
                $.post("RegistrationController",data,processresponse);
            }
        
    }
    
}
function processresponse(responseText){
    
    responseText=responseText.trim();
    alert(responseText);
    
    if(responseText==="success"){
        swal("success!","Registration Successful you Can Login","success");
        setTimeout(redirectUser,2000);
    }else if(responseText==="uap"){
        swal("Error!","Sorry the userID is Already present!","error");
    }else{
        swal("Error","Registration Failed Try Again","error");
    }
}
function validateUser(){
    if(username==="" || password==="" || address==="" ||cpassword===""||city===""||adhar===""||email===""||mobile==="")
    {
        swal("Error!","Fill All the details","error");
        return false;
    }
    return true;
}
function checkEmail(email){
    var atposition=email.indexOf("@");
    var dotposition= email.lastIndexOf(".");
    if(atposition<1||dotposition<atposition+2||dotposition+2>=email.length){
        swal("Error!","plleas enter valid email","error");
        return false ;
    }
    return true;
}
function redirectUser(){
    window.location="login.html";
}

