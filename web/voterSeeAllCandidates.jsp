
<%@page import="evoting.dao.CandidateDao"%>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.util.LinkedHashSet" %>

<%
 
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    response.sendRedirect("accessdenied.html");
    return;
}

LinkedHashSet<CandidateDetails> allCandidate=(LinkedHashSet)request.getAttribute("allCandidate");
System.out.println("in voterSeeAll Candidates JSP "+allCandidate);

StringBuffer displayBlock=new StringBuffer("<table border='2'  id='cTable'>");
    displayBlock.append("<br><tr><th>Candidate Id:</th><th>Candidate Name </th><th>City:</th><th>Party:</th><th>Symbol:</th></tr>");

        for(CandidateDetails c: allCandidate)
        {
             displayBlock.append("<tr><td>"+c.getCandidateId() +"</td><td>"+c.getCandidateName() +"</td><td>"+c.getCity()+"</td><td>"+c.getParty()
                     +"</td><td id='image'>"+"<img src='data:image/jpg;base64,"+c.getSymbol()+"'style='width:300px;height:200px;'/></td></tr>" );
          }
         displayBlock.append("</table><br><br><br>");

          out.println(displayBlock);  


%>