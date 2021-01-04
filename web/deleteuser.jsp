<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dao.UserDAO"%>
<%@page import="evoting.dao.VoteDao"%>
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

String uid=(String)request.getParameter("uid"); 
System.out.println("in deleteUSER JSP "+uid);
        try
        {  boolean already=false, result;
          if(VoteDao.getCandidateId(uid)!=null)                  // vote de chuka hoga
            {   
                already=true;
            }
          if(already)
          result=VoteDao.deleteVote(uid);
              
          result=UserDAO.deleteUser(uid);  // above .get(num) numbering is based on the order of data we have appended in the data object addcandidate() in adminoptions.js
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