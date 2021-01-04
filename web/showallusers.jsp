
<%@page import="evoting.dao.UserDAO"%>
<%@page import="evoting.dto.UserDetails" %>
<%@page import="java.util.ArrayList" %>

<%
 
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    response.sendRedirect("accessdenied.html");
    return;
}

StringBuffer displayBlock=new StringBuffer("<table border='1'>");
displayBlock.append("<tr><th>User Id:</th><th>UserName </th><th>Address:</th><th>City:</th><th>Email ID:</th><th>Mobile No:</th></tr>");

ArrayList<UserDetails> users=UserDAO.viewUsers();
    for(UserDetails u: users)
    {
        if(u.getUserid().equals("101"))
            continue;
        else
         displayBlock.append("<tr><td>"+u.getUserid() +"</td><td>"+u.getUsername() +"</td><td>"+u.getAddress()+"</td><td>"+u.getCity()+"</td><td>"+u.getEmail()+"</td><td>"+u.getMobile()+"</td></tr>" );
    
     
    }
     displayBlock.append("</table>");
          
      out.println(displayBlock);     
    
  

%>