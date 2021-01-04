

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    String result=(String)request.getAttribute("userType");
    String userid=(String)request.getAttribute("userid");
    
    if(userid!=null && result!=null){
        HttpSession sess= request.getSession();
        sess.setAttribute("userid", userid);
        if(result.equalsIgnoreCase("Admin"))
        {
            String url="AdminControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }else
        {
            String url="VotingControllerServlet;jsessionid="+session.getId();
            out.println(url);
        }
    }
    else{ 
        out.println("error");
    }
    
%>