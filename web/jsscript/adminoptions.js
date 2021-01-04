function redirectadministratorpage()
{
    swal("Admin","Redirecting to AdminActions Page !","success");  // to swal for 3 sec, we are creating this redirect page by JS function.
    setTimeout(function(){
      window.location="adminactions.jsp" ; //for restricting unauthorised access we are creating .jsp not.html & to maintain cookies by the session object
    },1000);
}
function redirectvotingpage()
{
    swal("Admin","Redirecting to AdminVoting Page !","success");  // to swal for 3 sec, we are creating this redirect page by JS function.
    setTimeout(function(){
      window.location="votermodule.jsp" ; //for restricting unauthorised access we are creating .jsp not.html & to maintain cookies by the session object
    },1000);
}

function managecandidate()
{  
    swal("Admin","Redirecting to Candidate Management","success");
    setTimeout(function()
    {
        window.location="managecandidate.jsp";
    },1000);
}

function manageuser()
{  
    swal("Admin","Redirecting to Users Management","success");
    setTimeout(function()
    {
        window.location="manageuser.jsp";
    },1000);
}

function showaddcandidateform()
{
removecandidateForm();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Add New Candidate</h3>";    // "multipart/form-data" means data is containing plainText with images. 
newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
var addcand=$("#result")[0];       // i.e, inserting these form inside result of managecandidate.jsp 
    addcand.appendChild(newdiv);    // try only    .append() when it is not picked up as Array element;
    
    data={xid:"getid"};    // to find the candidateId through 
   $.post("AddCandidateControllerServlet",data,function(responseText)    // processResponse() inside annonymus func
    {  
        $("#cid").val(responseText);
        $('#cid').prop("disabled",true);   // so that cid value  can't changed by the user.
        
    });
}


function getdetails(e)
{
    if (e.keyCode === 13) {         // i.e, to respond when we press ENTER.
        data={uid:$("#uid").val()};
       $.post("AddCandidateControllerServlet",data,function(responseText)
       {
         responseText=responseText.trim();        // cities ki list and user ka naam (or wrong in username)
           var i=responseText.lastIndexOf(",");
	  $('#city').empty(); //if 1 candidate ki record add hogya hai, fir se add krna hoga to ye function chalega,
                             // then our <select> already has city names, to currennt candidate ke liye cities fir se append ho jagegi <select> pe
                            // jo ise call krta hai uske sare child tags hata deta hai. 
          $('#city').append(responseText.substring(0,i)); 
          var uname= responseText.substring(i+1,responseText.length);  // will be added in userName
	  if(uname==="wrong")
		swal("Wrong Adhar!","Adhar no not found in DB","error");
          else
          {
                $('#cname').val(uname);   // jo userName aaya hai, insert it inside Candidate Name.
                $("#cname").prop("disabled",true);   // so that cname value  can't changed by the user.
	  }	
         
       });
    }
}   


function addcandidate()                     // our data is in the form to be send to the server
{                                               
     var form = $('#fileUploadForm')[0];  // form ka representative obj reference aaagya
    var data = new FormData(form);   //when data including text and images is to be stored in server, then we use fromdata object, takes argument as the from representative ref. So this Line also contains the image from the form.
    var cid=$("#cid").val();
    var uid=$("#uid").val();
    var cname=$("#cname").val();
    var party=$("#party").val();
    var city=$("#city").val();
    
    
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    $.ajax({                    // get,post internally calls this ajax function().
            type: "POST",
            enctype: 'multipart/form-data',      // "multipart/form-data" means data is containing plainText with images. 
            url: "AddNewCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            
            success: function (data) // it will be run when resquestResponse will sucessfully run 
            {
                str=data+"....";
                swal("Admin!", "Candidate Registered Successfully", "success");
                setTimeout(function()
                {
                  showaddcandidateform();

                },2000);
            },

            error: function (e) // it will be run when processResponse won't sucessfully run 
            {
                swal("Admin!", e, "error");
            }
        });
}

function showcandidate()   
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");   // jo div add(append) kar rahe hai uski ID
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
     newdiv.innerHTML = "<h3>Show Candidate</h3>";
 newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate id:</div><div><select id='cid'></select></div";
 newdiv.innerHTML = newdiv.innerHTML + "<span id = 'addresp'></span><br><br><br><br>";
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);  //web page me result name ki component ke andar ye pura div add ho jayega
 data ={data:"cid"};  // cid is only string here not value
 
    $.post("ShowCandidateControllerServlet",data,function(responseText){
        $("#cid").append(responseText);   // select dropdown ki id hai='cid' ,jisme options aayege
        
    });
    
    // $("#cid").change(function(){}  )
    $("#cid").on('change',function()   //here we are setting an CLICK(Selecting) event handler for the dropdown  // select me kuch choose karte hai to Change() EVENT fire hota hai, same here in jQuery style
    {               // (this) is dropdown
        var cid=$(this).children("option:selected").val(); // via jQuery jo option selected hai us option se candidate ki id utha rha hai
        data={data:cid};
         $.post("ShowCandidateControllerServlet",data,function(responseText)
         {
            clearText();
            $("#addresp").append(responseText);  //span ki id hai addresp jisme table form me candidate ki full details aayegi
         });
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

function electionresult()
{
  
 var data={data:"result"};
 
   $.post("ElectionResultControllerServlet",data,function(responseText)
 
    {
       
        swal("success","Below are the Election Results :","success");

        $("#result").html("");
  
        $("#result").html(responseText);

    });

}


function showupdatecandidateform()
{   
    removecandidateForm();
     var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");   // jo div add(append) kar rahe hai uski ID
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px");
    newdiv.setAttribute("border","solid 2px red");
     newdiv.innerHTML = "<h3>Update Candidate</h3>";

newdiv.innerHTML=newdiv.innerHTML+"<table><tr><th>Candidate id:</th><td><select id='cid'></select></td></tr></table>";
newdiv.innerHTML=newdiv.innerHTML+"<span id='addresp'></span><br><br><br><br>";
 
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);  //web page me result name ki component ke andar ye pura div add ho jayega
 data ={data:"cid"};  // cid is only string here not value
                                        
    $.post("UpdateCandidateController",data,function(responseText){
         
        $("#cid").append(responseText);   // select dropdown ki id hai='cid' ,jisme options aayege
        
         
         
    });
    
 $("#cid").on('change',function()   //here we are setting an CLICK(Selecting) event handler for the dropdown  // select me kuch choose karte hai to Change() EVENT fire hota hai, same here in jQuery style
    {               // (this) is dropdown
        var cid=$(this).children("option:selected").val(); // via jQuery jo option selected hai us option se candidate ki id utha rha hai
        data={data:cid};
         $.post("UpdateCandidateController",data,function(responseText)
         {
            clearText();
            $("#addresp").append(responseText);  //span ki id hai addresp jisme table form me candidate ki full details aayegi
          
            data={uid:$("#uid").val()};
            $.post("AddCandidateControllerServlet",data,function(responseText)
            {
                responseText=responseText.trim();        // cities ki list and user ka naam (or wrong in username)
                var i=responseText.lastIndexOf(",");
                $('#city').empty();  
                $('#city').append(responseText.substring(0,i)); 
         
         
            });
          
         
         
         });
    });
         
      
    
    
}

function updatecandidate()
{
     var form = $('#fileUploadForm')[0];  // form ka representative obj reference aaagya
    var data = new FormData(form);   //when data including text and images is to be stored in server, then we use fromdata object, takes argument as the from representative ref. So this Line also contains the image from the form.
    var cid=$("#cid").val();
    var uid=$("#uid").val();
    var cname=$("#cname").val();
    var party=$("#party").val();
    var city=$("#city").val();
    
    
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    $.ajax({                    // get,post internally calls this ajax function().
            type: "POST",
            enctype: 'multipart/form-data',      // "multipart/form-data" means data is containing plainText with images. 
            url: "UpdateNewCandidateController",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            
            success: function (data) // it will be run when resquestResponse will sucessfully run 
            {
                str=data+"....";
                swal("Admin Success!","Sucessfully Updated Canididate", "success");
                setTimeout(function()
                {
                  showupdatecandidateform();

                },2000);
            },

            error: function (e) // it will be run when processResponse won't sucessfully run 
            {
                swal("Error!", e, "error");
            }
        });
}
    


function deletecandidate()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");   // jo div add(append) kar rahe hai uski ID
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px"); 
    newdiv.setAttribute("border","solid 2px red");
     newdiv.innerHTML = "<h3>Remove Candidate</h3>";
 newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate id:</div><div><select id='cid'></select></div";
 newdiv.innerHTML = newdiv.innerHTML + "<span id = 'addresp'></span><br><br><br><br>";
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);  //web page me result name ki component ke andar ye pura div add ho jayega
 data ={data:"cid"};  // cid is only string here not value
 
    $.post("ShowCandidateControllerServlet",data,function(responseText){
        $("#cid").append(responseText);   // select dropdown ki id hai='cid' ,jisme options aayege
        
    });
    
    // $("#cid").change(function(){}  )
    $("#cid").on('change',function()   //here we are setting an CLICK(Selecting) event handler for the dropdown  // select me kuch choose karte hai to Change() EVENT fire hota hai, same here in jQuery style
    {               // (this) is dropdown
        var cid=$(this).children("option:selected").val(); // via jQuery jo option selected hai us option se candidate ki id utha rha hai
        data={data:cid};
         $.post("ShowCandidateControllerServlet",data,function(responseText)
         {
            clearText();
            $("#addresp").append(responseText);  //span ki id hai addresp jisme table form me candidate ki full details aayegi
            
             var newdiv2=document.createElement("div");
                                       
 newdiv2.innerHTML = newdiv2.innerHTML+" <input type='button' id='cnf' value='Confirm !'  >";
    
 var addPrd = $("#addresp");     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.append(newdiv2);
         
   //$("#cnf").onclick =function (){ 
 //  document.getElementById("cnf").addEventListener("click",  function(){ 
 
      $("#cnf").click(function(){    
   
           //  alert("delete "+cid);    
        $.post("deletecandidate.jsp?cid="+cid,function(responseText)
        {
            swal("Candidate Deleted !", responseText.trim(), "success");
            setTimeout(function()
                {
                  deletecandidate();

                },2000);
        });
      });
      
   
         
         });
    });
}

function showusers()
{  
   removecandidateForm();
     var newdiv=document.createElement("div");
      newdiv.setAttribute("id","candidateform");  
     newdiv.innerHTML = "<h3>Users Lists :</h3>";
newdiv.innerHTML=newdiv.innerHTML+"<span id='addresp'></span>";
 
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);
    $.post("showallusers.jsp",function(responseText)
         {
            //clearText();
            $("#addresp").append(responseText);  //span ki id hai addresp jisme table form me candidate ki full details aayegi
         });
         
         
         
}

function removeusers()
{  
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");   // jo div add(append) kar rahe hai uski ID
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","12px"); 
    newdiv.setAttribute("border","solid 2px red");
     newdiv.innerHTML = "<h3>Remove Candidate</h3>";
 newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>USER id:</div><div><select id='uid'></select></div";
 newdiv.innerHTML = newdiv.innerHTML + "<span id = 'addresp'></span><br><br><br><br>";
 var addPrd = $("#result")[0];     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.appendChild(newdiv);  //web page me result name ki component ke andar ye pura div add ho jayega
 data ={data:"uid"};  // cid is only string here not value

    $.post("ShowUserServlet",data,function(responseText){
        $("#uid").append(responseText);   // select dropdown ki id hai='cid' ,jisme options aayege
         
    });
    
    // $("#cid").change(function(){}  )
    $("#uid").on('change',function()   //here we are setting an CLICK(Selecting) event handler for the dropdown  // select me kuch choose karte hai to Change() EVENT fire hota hai, same here in jQuery style
    {               // (this) is dropdown
        var uid=$(this).children("option:selected").val(); // via jQuery jo option selected hai us option se candidate ki id utha rha hai
        data={data:uid};
       
         $.post("ShowUserServlet",data,function(responseText)
         {
            clearText();
            $("#addresp").append(responseText);  //span ki id hai addresp jisme table form me candidate ki full details aayegi
            
             var newdiv2=document.createElement("div");
                                       
 newdiv2.innerHTML = newdiv2.innerHTML+" <input type='button' id='cnf' value='Confirm !'  >";
    
 var addPrd = $("#addresp");     // i.e, inserting these form inside result of managecandidate.jsp
 addPrd.append(newdiv2);
         
   //$("#cnf").onclick =function () 
   // document.getElementById("cnf").addEventListener("click",  function(){ 
  
       $("#cnf").click(function(){ 
             data ={uid:uid};
        $.post("deleteuser.jsp",data,function(responseText)
        {   //alert("delete "+uid);    
            swal("USER Removed !", responseText.trim(), "success");
            setTimeout(function()
                {
                  removeusers();

                },2000);
        });
      });
      
   
         
         });
    });
    
    
    
    
}



