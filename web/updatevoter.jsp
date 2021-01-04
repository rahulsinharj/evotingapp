
<%@page import="evoting.dao.CandidateDao"%>
<%@page import="evoting.dto.UserDetails" %>
<%@page import="java.util.LinkedHashSet" %>

<%
 
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    response.sendRedirect("accessdenied.html");
    return;
}
System.out.println("in updateVoter JSP");
UserDetails user=(UserDetails)request.getAttribute("user");
              
StringBuffer displayBlock=new StringBuffer("");
    displayBlock.append("<div id='updateVoter'>"
    
    +"<div class='container register'><div class='row'><div class='col-md-3 register-left'>"
            +"<img src='https://image.ibb.co/n7oTvU/logo_white.png' alt=''/><h2>UPDATE <br>E-Voting System Profile</h2></div>"
        +"<div class='col-md-9 register-right'><div class='tab-content' id='myTabContent'>"
         +"<div class='tab-pane fade show active' id='home' role='tabpanel' aria-labelledby='home-tab'>"
        +"<h3 class='register-heading'>Update Here :</h3><div class='row register-form'>"
        +"<div class='col-md-6'><div class='form-group'>"
         +"<input type='text' class='form-control' id='username'  value='"+user.getUsername()+"' /></div>"
         +"<div class='form-group'><input type='text' id='adhar' name='txtAdhar' class='form-control' value='"+user.getUserid()+"' disabled  /></div>"
            +"<div class='form-group'><input type='text' class='form-control' id='city' placeholder='City *' value='' /></div>"
         +"<div class='form-group'><input type='password' id='password' class='form-control' placeholder='Password *' value='' /></div>"
         +"<div class='form-group'><input type='password' id='cpassword' class='form-control'  placeholder='Confirm Password *' value='' /></div>"
         +"</div><div class='col-md-6'><div class='form-group'><input type='email' id='email' class='form-control' placeholder='Email *' value='' /></div>"
        +"<div class='form-group'><input type='text' id='mobile' name='txtEmpPhone' class='form-control' placeholder='Mobile No *' value='' /></div>"
      +"<div class='form-group'><input type='text' id='address' class='form-control' placeholder='Address *' value='' />"
    +"</div><input type='button' onclick='updateUser()' class='btnRegister'  value='Update Profile'/>"
   +"</div></div></div></div></div></div></div><br></div><br>"); 
    
        

          out.println(displayBlock);  


%>