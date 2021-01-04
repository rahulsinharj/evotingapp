<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="jsscript/voteroptions.js"></script>
        <script src="jsscript/jquery.js"></script>
         <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <title>Voter Options Page</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            System.out.println("in VoterModule");
            
            out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Admin Actions Page</div><br><br>"+
                    "<div class='logout'><a href='login.html'>Logout</a></div></div>"+
        "<div class='container'>"+
            "<div id='dv1'  onclick='seecandidates()'><img src='images/show.png' height='255px' width='250px'><br><h3>See Candidates</h3></div>"+
            "<div id='dv2' onclick='castvote()'><img src='images/vote2.png' height='250px' width='250px'><br><h3>Cast Vote</h3></div>"+
           "<div id='dv3' onclick='showupdateprofile()'><img src='images/updateVoter.png' height='250px' width='250px'><br><h3>Update Profile</h3></div>"+
            
           "<br><br><div align='center' id='result'></div>"+
        "</div>");
        %>
        
        <style>
            #cTable 
            {
                background-color: RosyBrown ;
            }
            
           
         </style> 
    </body>
</html>
