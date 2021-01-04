
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.util.ArrayList" %>

<%
 
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    response.sendRedirect("accessdenied.html");
    return;
}
String result=(String)request.getAttribute("result");
StringBuffer displayBlock=new StringBuffer("");
if(result.equals("candidateList"))
{
    ArrayList<String> candidateId= (ArrayList)request.getAttribute("candidateId");
    for(String c:candidateId)
    {
        displayBlock.append("<option values='"+c+"'>"+c+"</option>" );
        
    }
    out.println(displayBlock);
    
    
    
}
else if(result.equals("candidate"))
    {
        CandidateDetails candidate = (CandidateDetails)request.getAttribute("candidate");
        
    displayBlock.append("<div id='candidateform' float='left' padding-left='12px' border='solid 2px red' >"
         
 
+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>"
+"<table><tr><th>User Id:</th><td><input type='text' id='uid' value='"+candidate.getUserId() +"' disabled  ></td></tr>"
+"<tr><th>Candidate Name:</th><td><input type='text' id='cname' value='"+candidate.getCandidateName()  +"'  disabled></td></tr>"
+"<tr><th>City:</th><td><select id='city'></select></td></tr>"
+"<tr><th>Party:</th><td><input type='text' id='party' value='"+candidate.getParty()  +"' ></td></tr>"
+"<tr><th>Symbol:</th><td id='image'>"
+"<img src='data:image/jpg;base64,"+candidate.getSymbol()+"'style='width:300px;height:200px;'/></td></tr>"        
+"<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>"
        
+"<tr><th><input type='button' value='Update Candidate' onclick='updatecandidate()' id='addcnd'></th>"
+"<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>"

        );
   
    
          
      out.println(displayBlock);     
    
    
    }


%>