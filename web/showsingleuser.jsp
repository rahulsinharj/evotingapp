
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
 
String result=(String)request.getAttribute("result");
System.out.println("in showSingleUser JSP "+result);
StringBuffer displayBlock=new StringBuffer("");
if(result.equals("UserId"))
{
    ArrayList<String> uid= (ArrayList)request.getAttribute("UserId");
    for(String c:uid)
    {   if(c.equals("101"))
        continue;
        else
        displayBlock.append("<option values='"+c+"'>"+c+"</option>" );
       
    }
    
    out.println(displayBlock);
    
    
    
}

else if(result.equals("singleUser"))
{
      

UserDetails u=(UserDetails)request.getAttribute("singleUser");
        displayBlock.append("'<table>"
                            +"<tr><th>User Name:</th><td>"+u.getUsername()+"</td></tr>"
                            +"<tr><th>Email Id:</th><td>"+u.getEmail()+"</td></tr>"
                            +"<tr><th>Mobile No:</th><td>"+u.getMobile()+"</td></tr>"
                            +"<tr><th>Address:</th><td>"+u.getAddress()+"</td></tr>"
                            +"<tr><th>City:</th><td>"+u.getCity()+"</td></tr>"        
                            +"</table>'");
        System.out.println("in Show singleUser JSP else");
        out.println(displayBlock);
    }


%>


