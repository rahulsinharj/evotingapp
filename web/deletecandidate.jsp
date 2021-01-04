<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dao.CandidateDao"%>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.io.IOException" %>
<%@page import="java.sql.SQLException" %>

<%    
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    response.sendRedirect("accessdenied.html");
    return;
}
String cid=(String)request.getParameter("cid"); 
System.out.println("in deleteCandidate JSP"+cid);
        try
        {
          boolean result=CandidateDao.deleteCandidate(cid);  // above .get(num) numbering is based on the order of data we have appended in the data object addcandidate() in adminoptions.js
           if(result==true)
           {
               out.println("success");
           }
           else
           {
               out.println("failed");
           }
            
        }    
        catch(Exception e)
        {
            out.println("error");
            e.printStackTrace();
        }
        

%>