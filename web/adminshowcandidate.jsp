
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
        displayBlock.append("'<table>"
                            +"<tr><th>User Id:</th><td>"+candidate.getUserId()+"</td></tr>"
                            +"<tr><th>Candidate Name:</th><td>"+candidate.getCandidateName()+"</td></tr>"
                            +"<tr><th>City:</th><td>"+candidate.getCity()+"</td></tr>"
                            +"<tr><th>Symbol:</th><td id='image'>"
                            +"<img src='data:image/jpg;base64,"+candidate.getSymbol()+"'style='width:300px;height:200px;'/></td></tr>"
                            +"</table>'");
        
        out.println(displayBlock);
    }


%>