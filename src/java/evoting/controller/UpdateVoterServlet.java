/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;


import evoting.dao.UserDAO;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateVoterServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      RequestDispatcher rd = null;
     String data=(String)request.getParameter("data");  // TypeCast to (String) karna zaruri hai 
     HttpSession session=request.getSession();
     String userid=(String)session.getAttribute("userid");
      
     
        System.out.println("in UpdateVoter Servlet");
     if(userid==null)
     {
         session.invalidate();
         response.sendRedirect("accessdenied.html");
         return;
     }
     try
     {
         if(data!=null && data.equals("getVoter"))
         {
             UserDetails user=UserDAO.getUserbyId(userid);
              request.setAttribute("user",user);
              
            rd = request.getRequestDispatcher("updatevoter.jsp");
             
         }
       
    else 
    {
            UserDetails user=new UserDetails();
        user.setUserid(userid);    
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));
        user.setCity(request.getParameter("city"));
        user.setEmail(request.getParameter("email"));
        user.setMobile(request.getParameter("mobile"));
       
       
          boolean result=UserDAO.updateUser(user);  // above .get(num) numbering is based on the order of data we have appended in the data object addcandidate() in adminoptions.js
           if(result==true)
           {
               rd=request.getRequestDispatcher("success.jsp");
           }
           else
           {
               rd=request.getRequestDispatcher("failure.jsp");
           }
                   
      }
          
   }
 
     catch(SQLException e)
     {
        request.setAttribute("exception",e);
        rd = request.getRequestDispatcher("showexception.jsp");
         e.printStackTrace();
     }
     
     finally
     {
           rd.forward(request,response);
     }
  }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
