 <%
 System.out.println("in noCache");
     
//Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-cache");

//Directs caches not to store the page under any circumstance 
response.setHeader("Cache-Control","no-store");

//Causes the proxy cache to see the page as "stale" 
response.setDateHeader("Expires", 0); 

//HTTP 1.0 backward compatibility
response.setHeader("Pragma","no-cache");    
session.removeAttribute("userid");
session.invalidate();

 %>