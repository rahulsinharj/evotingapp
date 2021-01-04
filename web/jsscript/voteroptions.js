function castvote()
{
    swal("Voter","Redirecting to VoteCasting Page !","success");  // to swal for 3 sec, we are creating this redirect page by JS function.
    setTimeout(function(){
      window.location="VerifyVotingServlet" ; //for restricting unauthorised access we are creating .jsp not.html & to maintain cookies by the session object
    },1000);
}
function showupdateprofile()
{  
   $("#result").html("");
   removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");   // jo div add(append) kar rahe hai uski ID
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
     newdiv.innerHTML = "<h3>Update Profile Below :</h3>";
 newdiv.innerHTML = newdiv.innerHTML + "<span id = 'addresp'></span>";
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);  
 
    data={data:"getVoter"};
   $.post("UpdateVoterServlet",data,function(responseText)
         {
            clearText();
            $("#addresp").append(responseText);  //span ki id hai addresp jisme table form me candidate ki full details aayegi
            //document.getElementById("updateVoter").style.border = "thick dotted MediumVioletRed";
            $("#updateVoter").css("border", "thick outset  MediumVioletRed ");
        });

}

function updateUser()
{
      
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
        else
        {
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
    $.post("UpdateVoterServlet",data,function(responseText)    // processResponse() inside annonymus func
    {  
       responseText=responseText.trim();
    alert(responseText);
    
    if(responseText==="success")
    {
        swal("success!","Updation Successful !!","success");
        $("#result").html("");
    }
    else
    {
        swal("Error","Updation Failed Try Again !!","error");
    }
       
       
       
       
       
       
        
    });
  
           
   }
        
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


function seecandidates()
{
     $("#result").html("");
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");   // jo div add(append) kar rahe hai uski ID
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
     newdiv.innerHTML = "<h3>Election Candidate Lists :</h3>";
 newdiv.innerHTML = newdiv.innerHTML + "<span id = 'addresp'></span>";
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);  
   $.post("VoterSeeAllCandidatesServlet",function(responseText)
 
    {
       $("#addresp").append(responseText);
      swal("success","Below are then Election Candidates :","success");
       
    });

}
 function removecandidateForm()    // jo form abhi currently dikh raha hai , kisi candidate ke details ka ; usko remove kaega ye func
{
    var contdiv=document.getElementById("result");   // getting div, i.e, jis div ke andar form hai
    var formdiv=document.getElementById("candidateform");  // getting form in that div
    if(formdiv!==null)  // it will be NULL when show se pehle add,update, delete inme se kuch na chala ho
    {                   
        $("#candidateform").fadeOut("3000");             
        contdiv.removeChild(formdiv);  // 'result' div which is in managecandidate.jsp me hai uska child div hai 'candidateform' (jisme cid ke list + select karne pe unke details aaayege) which is been created in showcandidate() func    
    }
    
} 
function clearText()
{
    $("#addresp").html("");   //addresp ke cid ke list nhi; selected candidate ke details aayege // works like innerHTML="" (i.e, blank)
}
