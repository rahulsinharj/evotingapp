<%-- 
    Document   : registrationtresponse
    Created on : 20 Dec, 2019, 11:02:09 AM
    Author     : satyam dada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    boolean result=(Boolean)request.getAttribute("result");
    boolean userfound=(Boolean)request.getAttribute("userfound");
    //out.println("result ="+result+"userfound ="+userfound);
    if(userfound==true){
        out.println("uap");
    }
    else if(result==true){
        out.println("success");
    }
    else{
        System.out.println("hhhhhhhhhhhhhhheeeeeeeeeerrrrrrrrrrreeeeeeeeee");
        out.println("error");
    }
%>